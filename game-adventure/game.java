class Adventure {
    static int playerX = 36;
    static int playerY = 205;
    static int room = 0;
    static boolean hasKey = false;
    static boolean finished = false;
    static boolean paletteReady = false;

    public static void update() {
        if (!paletteReady) {
            ui.palset(2, 0x294A);
            ui.palset(3, 0x3670);
            ui.palset(4, 0x4840);
            ui.palset(6, 0x3DEF);
            ui.palset(7, 0x7FFF);
            ui.palset(9, 0x001F);
            ui.palset(10, 0x03E0);
            ui.palset(11, 0x03EF);
            ui.palset(12, 0x7FE0);
            ui.palset(14, 0x7D40);
            paletteReady = true;
        }
        ui.cls(0);

        ui.print("LUPI JAVA: A CHAVE PERDIDA", 12, 10, 7);
        ui.print("Setas movem | Z interage", 12, 26, 6);
        ui.print("Explorando sala", 12, 44, 7);

        ui.rectfill(0, 224, 479, 269, 2);
        ui.rectfill(0, 220, 479, 224, 3);

        if (room == 0) {
            ui.print("A floresta esconde uma chave.", 34, 75, 10);
            ui.rectfill(190, 195, 205, 210, 14);
            ui.print("CHAVE", 177, 180, 7);
            ui.rectfill(430, 160, 458, 224, 4);
            ui.print("PORTA", 425, 145, 7);
            if (playerX > 175 && playerX < 220) {
                hasKey = true;
            }
        }

        if (room == 1) {
            ui.print("A caverna treme. Encontre a saida!", 30, 75, 11);
            ui.circfill(300, 203, 12, 9);
            ui.print("CRISTAL", 278, 180, 7);
            ui.rectfill(430, 160, 458, 224, 4);
            ui.print("SAIDA", 426, 145, 7);
            if (playerX > 280 && playerX < 325) {
                hasKey = true;
            }
        }

        if (room == 2) {
            ui.print("Voce chegou ao topo da torre!", 55, 75, 10);
            ui.print("A aventura continua...", 104, 100, 7);
            finished = true;
        }

        if (ui.btn(LEFT) && playerX > 8) {
            playerX = playerX - 2;
        }
        if (ui.btn(RIGHT) && playerX < 464) {
            playerX = playerX + 2;
        }
        if (ui.btn(UP) && playerY > 130) {
            playerY = playerY - 2;
        }
        if (ui.btn(DOWN) && playerY < 212) {
            playerY = playerY + 2;
        }

        if (ui.btnp(BTN_Z) && hasKey && playerX > 410) {
            room = room + 1;
            playerX = 24;
            hasKey = false;
        }

        if (hasKey) {
            ui.print("CHAVE: SIM", 350, 10, 14);
        }
        if (!hasKey) {
            ui.print("CHAVE: NAO", 350, 10, 6);
        }

        if (!finished) {
            ui.rectfill(playerX, playerY, playerX + 12, playerY + 18, 12);
            ui.rectfill(playerX + 3, playerY + 4, playerX + 5, playerY + 6, 7);
            ui.rectfill(playerX + 8, playerY + 4, playerX + 10, playerY + 6, 7);
        }
    }
}
