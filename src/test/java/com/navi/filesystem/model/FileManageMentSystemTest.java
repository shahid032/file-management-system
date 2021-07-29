package com.navi.filesystem.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.navi.filesystem.admin.Admin;
import com.navi.filesystem.admin.MemoryManageMentAdmin;
import com.navi.filesystem.exception.FileNotFoundException;
import com.navi.filesystem.exception.OutOfMemoryException;
import com.navi.filesystem.manager.MemoryBlockRetreiver;
import com.navi.filesystem.storage.FileStorage;
import com.navi.filesystem.storage.MemoryBlock;
import com.navi.filesystem.storage.MemoryStorage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * Considering chunk wise read and write. For simplicity one chunk is one String length in Unit testing
 * 
 * @author Shahid
 *
 */
class FileManageMentSystemTest {

  private IFileManageMentSystem fms;

  @BeforeEach
  public void setup() {
    FileStorage memoryStorage = new MemoryStorage(new HashMap<>(),
        new PriorityQueue<>(Comparator.comparing(MemoryBlock::getSize)));
    Admin memoryManager = new MemoryManageMentAdmin(memoryStorage);
    memoryManager.addMemoryBlocks(4, 2, 1);
    fms = new FileManagementSystem(new HashMap<>(), new MemoryBlockRetreiver(memoryStorage), memoryStorage);
  }

  @Test
  void openFileTest() {
    File file = fms.openFile("A");
    assertEquals("A", file.getName());
  }

  @Test
  void readFileTest() {
    fms.openFile("A");
    assertEquals("", fms.readFile("A"));
    assertThrows(FileNotFoundException.class, () -> fms.readFile("B"));
  }

  @Test
  void writeFileTest() {
    fms.openFile("A");
    fms.writeFile("A", "Some ");
    assertEquals("Some ", fms.readFile("A"));
  }

  @Test
  void appendToFileTest() {
    fms.openFile("A");
    fms.writeFile("A", "Some ");
    fms.appendToFile("A", "thing ");
    assertEquals("Some thing ", fms.readFile("A"));
  }

  @Test
  void deleteTest() {
    fms.openFile("A");
    fms.writeFile("A", "Some ");
    fms.appendToFile("A", "thing ");
    assertEquals("Some thing ", fms.readFile("A"));
    fms.deleteFile("A");
    assertThrows(FileNotFoundException.class, () -> fms.readFile("A"));
  }

  @Test
  void bigFileOverMultipleMemoryBlockTest() {
    fms.openFile("A");
    fms.writeFile("A", "Some ");
    fms.appendToFile("A", "thing ");
    fms.appendToFile("A", "some ");
    fms.appendToFile("A", "thing ");
    fms.appendToFile("A", "some ");
    assertEquals("Some thing some thing some ", fms.readFile("A"));
    fms.appendToFile("A", "some ");
    fms.appendToFile("A", "some ");
    fms.appendToFile("A", "some ");
    assertEquals("Some thing some thing some some some some ", fms.readFile("A"));
    assertThrows(OutOfMemoryException.class, () -> fms.appendToFile("A", "some "));

  }

}
