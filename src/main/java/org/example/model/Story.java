package org.example.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * класс для работы с историями.
 */
public class Story {
  private boolean nameFlag;
  protected static String name;
  protected Set<String> seasons;
  protected Map<String, ArrayList<String>> allSeasonsAndEpisodes;

  public Story() throws IOException {
    HtmlParser htmlParser = new HtmlParser();
    this.allSeasonsAndEpisodes = htmlParser.getEpisodesInSeasons(this);
    this.seasons = allSeasonsAndEpisodes.keySet();
  }

  public boolean getNameFlag() {
    return nameFlag;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) throws FileNotFoundException {
    HashMap<String, String> linkCheck = makeDictNames();
    for (String key : linkCheck.keySet()) {
      if (check(key, name)) {
        nameFlag = true;
      }
    }
    if (nameFlag) {
      Story.name = name;
    }
  }

  /**
   * @return linkNames названия, соответствующие выбранной истории, будут использованы для создания
   *     ссылки
   */
  public HashMap<String, String> makeDictNames() throws FileNotFoundException {
    String path = "C:/Users/admin/Desktop/bot2/data.txt";
    File file = new File(path);
    Scanner scannerF = new Scanner(file);
    HashMap<String, String> linkNames = new HashMap<>();
    while (scannerF.hasNextLine()) {
      String line = scannerF.nextLine();
      Pattern pattern = Pattern.compile("\"(.+?)\"");
      Matcher matcher = pattern.matcher(line);
      int i = 0;
      String[] pair = new String[2];
      while (matcher.find()) {
        pair[i] = matcher.group(1);
        i++;
      }
      linkNames.put(pair[0], pair[1]);
    }
    return linkNames;
  }

  public boolean check(String arg1, String arg2) {
    return Objects.equals(arg1, arg2);
  }

  /** функция выводит на экран названия всех доступных историй */
  public String printTitles() throws FileNotFoundException {
    ArrayList<String> keys = new ArrayList<>(makeDictNames().keySet());
    StringBuilder list = new StringBuilder();
    for (String key : keys) {
      list.append(key).append('\n');
    }
    return list.toString();
  }
}
