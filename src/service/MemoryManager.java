package service;

public class MemoryManager {

    private final int[] memoryBlocks = { 100, 200, 300 };

    public int allocateMemory(int size) {
        for ( int i = 0; i < memoryBlocks.length; i++ ){
            if ( memoryBlocks[i] >= size ){
                memoryBlocks[i] -= size;
                return i;
            }
        }
        return -1;
    }


    public void deallocateMemory(int index, int size) {
        if ( index >= 0 && index < memoryBlocks.length ){
            memoryBlocks[index] += size;
        }
    }
}
