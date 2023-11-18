package programing_3;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

class Process {
	int size;
	int startAddress;
	int endAddress;

	public Process() {
		Random random = new Random();
		// 프로세스 사이즈는 3~10
		size = random.nextInt(8) + 3;
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
}

class Hole {
	int startAddress;
	int endAddress;
	int size;

	// Hole만들때는 startAddress, endAddress인자로 줘야함.
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

	public void setSize(int endAddress, int startAddress) {
		this.size = endAddress - startAddress;
	}

}

public class MemorySimulation_FirstFit {
	public static void main(String[] args) {
		Queue<Process> processQueue = new LinkedList<Process>();
		ArrayList<Hole> hole = new ArrayList<Hole>();
		ArrayList<Process> process = new ArrayList<Process>();
		Hole firstHole = new Hole(0, 100);
		hole.add(firstHole);
		int num = 0;
		boolean externalFragmentation = false;
		while (!externalFragmentation) {
			// 프로세스 4개 생성
			for (int i = 0; i < 4; i++) {
				Process newProcess = new Process();
				processQueue.add(newProcess);
			}
			// 프로세스 큐에서 하나씩 꺼내면서 비교
			while (!processQueue.isEmpty()) {
				// 프로세스큐에서 하나씩 꺼냄
				for (int i = 0; i < 4; i++) {
					Process removedProcess = processQueue.poll();
					// hole리스트 순회하면서 하나씩 비교
					for (int j = 0; j < hole.size(); j++) {
						// 꺼낸 프로세스 사이즈가 홀의 사이즈보다 작다면
						if (removedProcess.size < hole.get(j).getSize()) {
							removedProcess.setStartAddress(hole.get(j).getStartAddress());
							removedProcess.setEndAddress(hole.get(j).getStartAddress() + removedProcess.size);
							hole.get(j).setStartAddress(removedProcess.getEndAddress());
							hole.get(j).setSize(hole.get(j).getEndAddress(), hole.get(j).getStartAddress());
							process.add(removedProcess);
							break;
						} else if (removedProcess.size == hole.get(j).getSize()) {
							removedProcess.setStartAddress(hole.get(j).getStartAddress());
							removedProcess.setEndAddress(hole.get(j).getStartAddress() + removedProcess.size);
							hole.remove(j);
							process.add(removedProcess);
							break;
						} else {
							// hole의 리스트를 다 순회했다면
							if (j == hole.size() - 1) {
								for (int k = 0; k < process.size(); k++) {
									System.out.printf("[%d]번째 프로세스 주소(%d, %d)\n", k, process.get(k).getStartAddress(),
											process.get(k).getEndAddress());
								}
								System.out.println("외부단편화 발생");
								externalFragmentation = true;
								break;
							}
							continue;
						}
					}
				}

				for (int i = 0; i < 2; i++) {
					Random random = new Random();
					Process removeProcess = new Process();
					if (process.size() <= 0) {
						System.out.println("프로세스스가 비었습니다.");
						break;
					}
					num = random.nextInt(process.size());
					removeProcess = process.get(num);
					if (process.size() == 0) {
						System.out.println("hole이 비어있습니다.");
						break;
					}

					for (int j = 0; j < hole.size(); j++) {
						// 프로세스의 end주소와 hole의 start주소가 같다면(삭제하려는 프로세스가 hole과 연속된다면
						if (removeProcess.getEndAddress() == hole.get(j).startAddress) {
							hole.get(j).setStartAddress(removeProcess.getStartAddress());
							break;
						}
						// 프로세스의 end주소와 홀의 start주소와 같은게 없다면
						else if (j == hole.size() - 1) {
							Hole newHole = new Hole(removeProcess.getStartAddress(), removeProcess.getEndAddress());
							hole.add(newHole);
							break;
						} else
							continue;
					}
					process.remove(num);

				}

			}

		}
	}

}
