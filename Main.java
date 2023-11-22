import java.util.List;

public class Main {
    public static void main(String[] args) {
        Theme theme = new Theme(); // Theme 객체 생성
        new ThemeFrame(theme); // ThemeFrame 객체 생성 후 Theme 객체 전달
    }
}

/*
 * 서로 코드 merge를 하면서 느낀건데
 * 현재 우리가 각자 코드를 작성하면서 서로 다르게 생각하던 부분이 있었음.
 * 조금 더 대화를 해서 방향성을 맞추고 코드를 작성하는게 좋을 것 같음.
 * + 지금부터 작업할때는 중심 공유 코드는 변경하지 않는 것이 좋을 것 같음.
 * 중심코드가 바뀌는 순간 전체가 뒤틀려서 코드를 다시 작성해야하는 상황이 발생함.
 * 11/23일자 첫 화면에서 주제선택하고 다음 9장중 2장 선택하는 Frame까지 연결해놓음.
 */