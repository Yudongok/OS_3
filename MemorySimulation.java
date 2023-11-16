package programing_3;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;


class Process{
	int size;
	int startAddress;
	int endAddress;
	public Process() {
		Random random = new Random();
		size = random.nextInt(8) + 3;
	}
	
	public void setStartAddress(int startAddress) {
		this.startAddress = startAddress;
	}
	public void setEndAddress(int endAddress) {
		this.endAddress = endAddress;
	}
}

class Hole{
	int startAddress;
	int endAddress;
	int size;
	
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
public class MemorySimulation {
	public static void main(String[] args) {
		Queue<Process> processQueue = new LinkedList<>();
		int main_size = 100;
		while(main_size != 0) {
			for(int i = 0; i < 4; i++) {
				Process newProcess = new Process();
				
			}
		}
	}

}
