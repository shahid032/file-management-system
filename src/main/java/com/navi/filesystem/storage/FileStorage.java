package com.navi.filesystem.storage;

public interface FileStorage {

  public String addMemoryBlock(Integer totalMemBlock, Integer chunkSize, Integer memBlockSize);

  public MemoryBlock removeMemoryBlock(Integer id) throws Exception;

  public MemoryBlock pollMostAvailableBlock();

  public MemoryBlock getMemoryBlockById(Integer id);

  public void addMemoryBlockToPQ(MemoryBlock memBlock);

}
