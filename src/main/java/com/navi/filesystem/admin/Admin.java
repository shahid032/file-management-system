package com.navi.filesystem.admin;

import com.navi.filesystem.storage.MemoryBlock;

public interface Admin {

  public String addMemoryBlocks(Integer memoryBlockSize, Integer totatMemoryBlocks, Integer chunkSize);
  
  public MemoryBlock removeMemoryBlock(Integer id) throws Exception;
  
}
