package com.navi.filesystem.storage;

import com.navi.filesystem.exception.OutOfMemoryException;

import java.util.ArrayList;
import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemoryBlock {

  private Integer id;

  private Integer size;

  @Builder.Default
  private List<Chunk> availableChunks = new ArrayList<>();

  public void addChunks(List<Chunk> chunk) {
    availableChunks.addAll(chunk);
  }

  public void addChunk(Chunk chunk) {
    availableChunks.add(chunk);
  }

  public Chunk pollAvailableChunk() {
    if (availableChunks.isEmpty()) {
      throw new OutOfMemoryException();
    }
    return availableChunks.remove(0);
  }

  public boolean isChunkAvailable() {
    return !availableChunks.isEmpty();
  }

}
