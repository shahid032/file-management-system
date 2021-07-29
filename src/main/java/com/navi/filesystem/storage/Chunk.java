package com.navi.filesystem.storage;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Chunk {

  private Integer chunkId;

  private String fileName;

  private String content;

  private Integer size;

  private Integer memoryBlockId;
  
  public void writeContent(String fileName, String content) {
    this.content = content;
    this.fileName = fileName;
  }

}
