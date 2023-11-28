package de.thanatos761.objects;

import java.nio.charset.StandardCharsets;

public class Ole {
    private final String[] OleBingo = {
            "<html>Das machen wir nie so!</html>",
            "<html>Warum ist NIEMAND in Cams!??</html>",
            "<html>Wir spielen wieder nur Bullshit!</html>",
            "<html>Hat jemand ein Schild für mich?</html>",
            "<html>Den hab ich gefickt!</html>",
            "<html>Wir machen Topfloor-Clear!</html>",
            "<html>Kennt denn niemand die Strat?</html>",
            "<html>Warum sterbt ihr alle so dämlich / schnell?</html>",
            "<html>Wieso ist der Randy so scheiße???</html>",
            "<html>Das spielen wir immer so!</html>",
            "<html>Wieso hält das niemand??</html>",
            "<html>Warum roamt ihr deep!??</html>",
            "<html>Warum sind alle am roamen?</html>",
            "<html>Ich werde wieder nur verkauft!</html>",
            "Diggah!",
            "<html>Ich muss das ganze Setup alleine machen!</html>"
    };

    public String[] getOleBingo() {
        return this.OleBingo;
    }

    public String[] getOleBingoUTF8() {
        String[] oleNew = new String[OleBingo.length];

        for(int i = 0; i < oleNew.length; i++) {
            byte[] biteMyAss = OleBingo[i].getBytes();
            String utf = new String(biteMyAss, StandardCharsets.UTF_8);
            oleNew[i] = utf;
        }

        return oleNew;
    }
}
