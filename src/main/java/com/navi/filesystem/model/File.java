package com.navi.filesystem.model;

import com.navi.filesystem.storage.Chunk;
import com.navi.filesystem.storage.FileStorage;
import com.navi.filesystem.storage.MemoryBlock;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

@Getter
public class File {

  private String name;

  private final List<Chunk> occupiedChunks;

  private Integer size;

  private FileStorage memoryStorage;

  public File(String name, FileStorage memFileStorage) {
    super();
    this.name = name;
    this.occupiedChunks = new ArrayList<>();
    this.size = 0;
    this.memoryStorage = memFileStorage;
  }

  public String fetchAllContent(File file) {
    StringBuilder sb = new StringBuilder();
    file.getOccupiedChunks().stream().filter(chunk -> chunk.getContent() != null)
        .forEach(chunk -> sb.append(chunk.getContent()));
    return sb.toString();
  }

  public void addChunk(Chunk chunk) {
    this.occupiedChunks.add(chunk);
    this.size = occupiedChunks.size();
  }

  public void clearChunk() {
    this.occupiedChunks.stream().forEach(chunk -> {
      MemoryBlock memBlock = memoryStorage.getMemoryBlockById(chunk.getMemoryBlockId());
      memBlock.addChunk(chunk);
    });
    this.occupiedChunks.clear();
    this.size = 0;
  }

  public List<Chunk> getAllchunks() {
    return this.occupiedChunks;
  }

}
