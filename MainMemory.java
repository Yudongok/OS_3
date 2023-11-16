package Simulation;

import java.util.ArrayList;

class Hole{
	private int startAddress;
	private int endAddress;
	private int size;
	
	public Hole(int startAddress, int endAddress) {
		this.startAddress = startAddress;
		this.endAddress = endAddress;
		this.size = endAddress - startAddress;
	}
	
	public int getStartAddress() {
		return startAddress;
	}
	
	public void setStartAddress(int startAddress) {
		this.startAddress = startAddress;
	}
	
	public int getEndAddress() {
		return endAddress;
	}
	
	public void setEndAddress(int endAddress) {
		this.endAddress = endAddress;
	}
	
	public int getSize() {
		return size;
	}
	
	public void setSize(int size) {
		this.size = size;
	}
}
public class MainMemory {
	public ArrayList<Hole> holeArrayList = new ArrayList<>();
	int mainSquareX, mainSquareY;
	int mainSquareW, mainSquareH;
	Process[] array = new Process[100]; 
	public MainMemory() {
		mainSquareX = 40;
		mainSquareY = 40;
		mainSquareW = 60;
		mainSquareH = 220;
		holeArrayList.add(new Hole(40, 260));
	}
	
	public ArrayList<Hole> getHoleArrayList(){
		return holeArrayList;
	}
	

}
