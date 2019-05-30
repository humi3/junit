# JUnit5
## 概要
JUnit5(Jupiter)では、テストクラスはpublicにする必要は無い。  
クラス名は何でもいいが、gradlew testコマンドでテストを実行する場合、デフォルトではクラス名の末尾がTest、Testsのものだけが実行対象となる。  
(gradlew Groovyで作られたビルドツール)
また、抽象クラスのメソッドやインターフェースのデフォルトメソッドに@Testアノテーションをつけてテストメソッドとすることができる。  
テストクラスやテストメソッドにタグを付けることができる。  
テスト実行時にタグがついたテストを実行したり、逆もしかり。  

参照資料
* [JUnit 5 ユーザガイド](https://udzuki.jp/public/junit5-user-guide-ja/#writing-tests-annotations)
* [JUnit5 テストクラス](http://www.ne.jp/asahi/hishidama/home/tech/java/junit/5/class.html#h_example)
