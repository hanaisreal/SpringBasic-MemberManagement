package hello.core.singleton;

public class SingletonService {

    //1. static으로 등록하면 클래스 레벨에 등록되기 때문에 객체를 딱 1개만 생성해둔다.
    private static final SingletonService instance = new SingletonService();

    //2. public으로 열어서 객체 인스턴스가 필요하면 이 static 메소드를 통해서만 조회하도록 허용한다.
    public static SingletonService getInstance(){
        return instance;
    }

    //3. constructor를 private으로 선언해서 외부에서 new 키워드를 사용한 객체 생성을 못하게 막는다.
    private SingletonService(){

    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}
