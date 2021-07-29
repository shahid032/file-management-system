package com.navi.filesystem.admin;

import com.navi.filesystem.storage.FileStorage;
import com.navi.filesystem.storage.MemoryBlock;

public class MemoryManageMentAdmin implements Admin {

  private final FileStorage memoryBlockStorage;

  public MemoryManageMentAdmin(FileStorage memoryBlockStorage) {
    super();
    this.memoryBlockStorage = memoryBlockStorage;
  }

  @Override
  public String addMemoryBlocks(Integer size, Integer count, Integer chunkSize) {
    return memoryBlockStorage.addMemoryBlock(count, chunkSize, size);
  }

  @Override
  public MemoryBlock removeMemoryBlock(Integer id) throws Exception {
    return memoryBlockStorage.removeMemoryBlock(id);
  }

}
