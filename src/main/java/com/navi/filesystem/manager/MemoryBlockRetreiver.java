package com.navi.filesystem.manager;

import com.navi.filesystem.storage.Chunk;
import com.navi.filesystem.storage.FileStorage;
import com.navi.filesystem.storage.MemoryBlock;

public class MemoryBlockRetreiver implements BlockRetreiver {

  private final FileStorage memoryStorage;

  public MemoryBlockRetreiver(FileStorage memoryStorage) {
    super();
    this.memoryStorage = memoryStorage;
  }

  @Override
  public Chunk fetchAvailableChunk() {
    MemoryBlock memBlock = memoryStorage.pollMostAvailableBlock();
    Chunk chunk = memBlock.pollAvailableChunk();
    memoryStorage.addMemoryBlockToPQ(memBlock);
    return chunk;
  }

}
