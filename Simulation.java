package Simulation;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

class Rectangle{
	int x, y, w, h;
}

class MyPanel extends JPanel implements ActionListener{
	JButton button1;
	JButton button2;
	MainMemory mainMemory = new MainMemory();
    Process process = new Process();
	int array_num = 0;
	int hole_num = 0;
	int processNumber = 4;
	int squareX, squareY;
	int squareW, squareH;
	Queue<Process> processQueue = new LinkedList<>();
	//holes에 start, end, holesize 저장
	ArrayList<Hole> holes = mainMemory.getHoleArrayList();
	ArrayList<Process> processList = new ArrayList<>();
	
	public MyPanel() {
		setLayout(new FlowLayout());
		button1 = new JButton("생성");
		button2 = new JButton("삭제");
		button1.addActionListener(this);
		button2.addActionListener(this);
		add(button1);
		add(button2);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == button1) {
			//생성버튼 클릭시 프로세스 4개생성 후 프로세스큐에 할당.
			for(int i = 0; i<processNumber; i++) {
				Process newProcess = new Process();
				processQueue.add(newProcess);
			}
			//프로세스 큐가 빌때까지 적절한 위치에 프로세스 삽입.
			while(!processQueue.isEmpty()) {
				Process removedProcess = processQueue.poll();
				if(holes.size() == 0) {
					//외부단편화 발생
				}
				else {
					for(int i = 0; i < holes.size(); i++) {
						if(removedProcess.size < holes.get(i).getSize()) {
							holes.get(i).setStartAddress(holes.get(i).getStartAddress() + removedProcess.size);
							holes.get(i).setSize(holes.get(i).getSize() + removedProcess.size);
							//시작주소
							removedProcess.setY(holes.get(i).getStartAddress());
							//끝 주소
							removedProcess.setH(removedProcess.getY() + removedProcess.size);
							processList.add(removedProcess);
							repaint();
						}
						else if(removedProcess.size == holes.get(i).getSize()) {
							holes.remove(i);
							removedProcess.setY(holes.get(i).getStartAddress());
							removedProcess.setH(holes.get(i).getEndAddress());
							processList.add(removedProcess);
							repaint();
						}
						else if(removedProcess.size > holes.get(i).getSize()) {
							continue;
						}
							
						}
					}
				}
			
		}
		if(e.getSource() == button2) {
			Random random = new Random();
			//나중에 2를 변수로 바꿔야됨 magicNumber위험
			for(int i = 0; i < 2; i++) {
				int removeIndex = random.nextInt(processList.size());
				Process removedProcess1 = processList.get(removeIndex);
				int start, end;
				start = removedProcess1.startAddress;
				end = removedProcess1.endAddress;
				Hole newHole = new Hole(start, end);
				holes.add(newHole);
				processList.remove(removeIndex);
				
			}
			repaint();
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//mainMemory 그리기
		squareX = mainMemory.mainSquareX;
		squareY = mainMemory.mainSquareY;
		squareW = mainMemory.mainSquareW;
		squareH = mainMemory.mainSquareH;
		g.drawRect(squareX, squareY, squareW, squareH);
		
		for(Process process1 : processList) {
			int processX = process1.getX();
			int processY = process1.getY();
			int processW = process1.getW();
			int processH = process1.getH();
			
			Color randomColor = new Color((int) (Math.random() * 0x1000000));
			
			g.setColor(randomColor);
			g.fillRect(processX, processY, processW, processH);
			
			g.drawRect(processX, processY, processW, processH);
		}
	}

}

public class Simulation extends JFrame {
	public Simulation() {
		setSize(300, 300);
		setTitle("램 시뮬레이션");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(new MyPanel());
		setVisible(true);
	}
	
	public static void main(String[] args) {
		Simulation r = new Simulation();
	}
	
}

