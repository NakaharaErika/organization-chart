package serviceTest;

import static org.junit.Assert.*;

import java.lang.reflect.Method;

import org.junit.Test;

import service.Tantai;

public class TantaiTest {

    @Test
    public void callProtecTest() throws Exception {
        // Tantai クラスのインスタンスを生成
        Tantai tantai = new Tantai();

        // Tantai クラスの 'callProtec' メソッドを取得
        // getDeclaredMethod を使って、メソッド名とパラメータの型を指定
        Method method = Tantai.class.getDeclaredMethod("callProtec", String.class);

        // メソッドへのアクセス権を設定
        // protected メソッドでもアクセス可能にする
        method.setAccessible(true);

        // メソッドを実行し、結果を取得
        // invoke で実際にメソッドを呼び出す
        // 第一引数はメソッドを実行するオブジェクト、第二引数はメソッドの引数
        boolean flg = (boolean) method.invoke(tantai, "testStr!");

        // 結果を検証
        assertEquals(true, flg);
    }

    @Test
    public void callPriTest() throws Exception {
        // Tantai クラスのインスタンスを生成
        Tantai tantai = new Tantai();

        // Tantai クラスの 'callPri' メソッドを取得
        // private メソッドでもアクセス可能にするために getDeclaredMethod を使用
        Method method = Tantai.class.getDeclaredMethod("callPri", String.class);

        // メソッドへのアクセス権を設定
        // private メソッドでもアクセス可能にする
        method.setAccessible(true);

        // メソッドを実行し、結果を取得
        boolean flg = (boolean) method.invoke(tantai, "testStr2!");

        // 結果を検証
        assertEquals(true, flg);
    }
}
