package io.ggogit.ggogit.util;

public class EmailTemplate {

    public static String joinEmail(String link) {
        return """
            <body>
            <header style="display: flex; justify-content: center; font-size: 24px; font-weight: bold; margin: 50px 0 10px 0">
              <p>GGogit</p>
            </header>
            
            <main>
              <section style="display: flex; justify-content: center; align-items: center; flex-direction: column; margin-bottom: 100px;">
                <h1 style="display: none" >가입 안내글</h1>
                <div style="border: solid 2px gray; border-radius: 4px; padding: 16px; width: 400px;  background-color: #F5F8F1; margin: 10px 0">
                  <div>회원가입을 계속하시려면 하단의 링크를 클릭해주세요. 만약 본인이 요청하지 않거나  실수로 요청하였다면  이 메일을 무시하고 삭제해주시기 바랍니다.</div>
                </div>
                <a href="%s" style="background-color: #323A27; width: 400px; padding: 16px; border-radius: 4px; font-size: 16px; color: white; text-align: center; margin-top: 10px;">회원가입</a>
                <div style="margin: 20px 0">위 버튼을 클릭 또는 아래의 링크를 클릭해주세요.</div>
                <a href="%s" style="margin: 20px 0; text-decoration: underline;">%s</a>
                <div style="margin: 20px 0">위 링크는 4시간동안 유효합니다.</div>
              </section>
            </main>
            </body>
        """.formatted(link, link, link);
    }
}
