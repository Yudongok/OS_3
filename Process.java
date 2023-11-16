package Simulation;
import java.util.Random;

public class Process {
	int size;
	int startAddress;
	int endAddress;
	//startAddress = processSquareY, endAddress = processSquareH
	int processSquareX, processSquareY, processSquareW, processSquareH;
	public Process() {
		Random random = new Random();
		size = random.nextInt(8) + 3;
		processSquareX = 40;
		processSquareW = 60;
	}
	
	public int getX() {
		return processSquareX;
	}
	
	public void setX(int x) {
		this.processSquareX = x; 
	}
	
	public int getY() {
		return processSquareY;
	}
	
	public void setY(int y) {
		this.processSquareY = y; 
	}
	
	public int getH() {
		return processSquareH;
	}
	
	public void setH(int H) {
		this.processSquareH = H; 
	}
	
	public int getW() {
		return processSquareW;
	}
	
	public int getStartAddress() {
		return processSquareY;
	}
	
	public void setStartAddress(int startAddress) {
		this.startAddress = startAddress;
	}
	
	public int getEndAddress() {
		return processSquareH;
	}
	
	public void setEndAddress(int endAddress) {
		this.endAddress = endAddress;
	}
	

	

}
