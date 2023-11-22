import java.util.List;

public class Main {
    public static void main(String[] args) {
        Theme theme = new Theme(); // Theme 객체 생성
        new ThemeFrame(theme); // ThemeFrame 객체 생성 후 Theme 객체 전달
    }
}