package com.navi.filesystem.manager;

import com.navi.filesystem.storage.Chunk;

public interface BlockRetreiver {
  
  public Chunk fetchAvailableChunk();

}
