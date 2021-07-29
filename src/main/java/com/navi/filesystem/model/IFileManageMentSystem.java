package com.navi.filesystem.model;

/**
 * 
 * @author shahid
 *
 */
public interface IFileManageMentSystem {

  public File openFile(String fileName);

  public String readFile(String fileName);

  public File writeFile(String name, String content);

  public File appendToFile(String name, String content);

  public File deleteFile(String name);

}
