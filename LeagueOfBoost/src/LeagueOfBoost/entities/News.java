package LeagueOfBoost.entities;

public class News {
  private int id;
  private String titre;
  private String description;
  private String short_description;
  private String img;
  private String date;
  private String etiquette;
  private double score;
  private int nb_vues = 0;

  private static int NewsId;
  public static int actionTest = 0;
  private static String searchValue = null;
  private static double searchImageScore = -1;
  private static String searchImageEtiquette = null;

  public News(int id, String titre, String description, String short_description, String img, String date) {
    this.id = id;
    this.titre = titre;
    this.description = description;
    this.short_description = short_description;
    this.img = img;
    this.date = date;
  }

  public News() {
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTitre() {
    return titre;
  }

  public void setTitre(String titre) {
    this.titre = titre;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getShort_description() {
    return short_description;
  }

  public void setShort_description(String short_description) {
    this.short_description = short_description;
  }

  public String getImg() {
    return img;
  }

  public void setImg(String img) {
    this.img = img;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  @Override
  public String toString() {
    return "News [id=" + id + ", titre=" + titre + ", description=" + description + ", short_description="
        + short_description + ", img=" + img + ", date=" + date + "]";
  }

  public static int getNewsId() {
    return NewsId;
  }

  public static void setNewsId(int newsId) {
    NewsId = newsId;
  }

  public double getScore() {
    return score;
  }

  public void setScore(double score) {
    this.score = score;
  }

  public String getEtiquette() {
    return etiquette;
  }

  public void setEtiquette(String etiquette) {
    this.etiquette = etiquette;
  }

  public static double getSearchImageScore() {
    return searchImageScore;
  }

  public static void setSearchImageScore(double searchImageScore) {
    News.searchImageScore = searchImageScore;
  }

  public static String getSearchValue() {
    return searchValue;
  }

  public static void setSearchValue(String searchValue) {
    News.searchValue = searchValue;
  }

  public static String getSearchImageEtiquette() {
    return searchImageEtiquette;
  }

  public static void setSearchImageEtiquette(String searchImageEtiquette) {
    News.searchImageEtiquette = searchImageEtiquette;
  }

  public int getNb_vues() {
    return nb_vues;
  }

  public void setNb_vues(int nb_vues) {
    this.nb_vues = nb_vues;
  }

}
