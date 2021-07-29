package com.navi.filesystem.model;

import com.navi.filesystem.exception.FileNotFoundException;
import com.navi.filesystem.manager.BlockRetreiver;
import com.navi.filesystem.storage.Chunk;
import com.navi.filesystem.storage.FileStorage;

import java.util.Map;

public class FileManagementSystem implements IFileManageMentSystem {

  private final Map<String, File> existingFiles;

  private final BlockRetreiver memoryBlockRetreiver;

  private final FileStorage memoryStorage;

  public FileManagementSystem(Map<String, File> existingFiles, BlockRetreiver memoryBlockRetreiver,
      FileStorage memoryStorage) {
    super();
    this.existingFiles = existingFiles;
    this.memoryBlockRetreiver = memoryBlockRetreiver;
    this.memoryStorage = memoryStorage;
  }

  @Override
  public File openFile(String fileName) {
    File file = null;
    if (existingFiles.containsKey(fileName)) {
      file = existingFiles.get(fileName);
    } else {
      file = new File(fileName, memoryStorage);
      existingFiles.put(fileName, file);
    }
    return file;
  }

  @Override
  public String readFile(String fileName) {
    if (!existingFiles.containsKey(fileName)) {
      throw new FileNotFoundException();
    }
    File file = existingFiles.get(fileName);
    return file.fetchAllContent(file);
  }

  @Override
  public File writeFile(String name, String content) {
    if (!existingFiles.containsKey(name)) {
      throw new FileNotFoundException();
    }
    File file = existingFiles.get(name);
    synchronized (file) {
      file.clearChunk();
      Chunk chunk = memoryBlockRetreiver.fetchAvailableChunk();
      chunk.writeContent(name, content);
      file.addChunk(chunk);
    }
    return file;
  }

  @Override
  public File appendToFile(String name, String content) {
    if (!existingFiles.containsKey(name)) {
      throw new FileNotFoundException();
    }
    File file = existingFiles.get(name);
    synchronized (file) {
      Chunk chunk = memoryBlockRetreiver.fetchAvailableChunk();
      chunk.writeContent(name, content);
      file.addChunk(chunk);
    }
    return file;
  }

  @Override
  public File deleteFile(String name) {
    if (!existingFiles.containsKey(name)) {
      throw new FileNotFoundException();
    }
    File file = existingFiles.get(name);
    synchronized (file) {
      file.clearChunk();
      existingFiles.remove(name);
    }
    return file;
  }

}
