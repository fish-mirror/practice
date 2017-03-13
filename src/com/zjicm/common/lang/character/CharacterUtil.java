package com.zjicm.common.lang.character;

import com.dxy.base.util.StringUtil;
import com.dxy.commons.text.cache.CharacterNatures;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class CharacterUtil {
    public static final Map<Character, Character> RADICAL_KANGXI_TO_STD = new HashMap<Character, Character>();

    static {
        RADICAL_KANGXI_TO_STD.put((char) 0x2F00, (char) 0x4E00);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F01, (char) 0x4E28);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F02, (char) 0x4E36);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F03, (char) 0x4E3F);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F04, (char) 0x4E59);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F05, (char) 0x4E85);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F06, (char) 0x4E8C);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F07, (char) 0x4EA0);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F08, (char) 0x4EBA);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F09, (char) 0x513F);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F0A, (char) 0x5165);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F0B, (char) 0x516B);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F0C, (char) 0x5182);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F0D, (char) 0x5196);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F0E, (char) 0x51AB);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F0F, (char) 0x51E0);

        RADICAL_KANGXI_TO_STD.put((char) 0x2F10, (char) 0x51F5);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F11, (char) 0x5200);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F12, (char) 0x529B);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F13, (char) 0x52F9);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F14, (char) 0x5315);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F15, (char) 0x531A);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F16, (char) 0x5338);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F17, (char) 0x5341);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F18, (char) 0x535C);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F19, (char) 0x5369);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F1A, (char) 0x5382);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F1B, (char) 0x53B6);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F1C, (char) 0x53C8);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F1D, (char) 0x53E3);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F1E, (char) 0x56D7);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F1F, (char) 0x571F);

        RADICAL_KANGXI_TO_STD.put((char) 0x2F20, (char) 0x58EB);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F21, (char) 0x5902);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F22, (char) 0x590A);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F23, (char) 0x5915);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F24, (char) 0x5927);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F25, (char) 0x5973);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F26, (char) 0x5B50);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F27, (char) 0x5B80);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F28, (char) 0x5BF8);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F29, (char) 0x5C0F);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F2A, (char) 0x5C22);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F2B, (char) 0x5C38);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F2C, (char) 0x5C6E);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F2D, (char) 0x5C71);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F2E, (char) 0x5DDB);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F2F, (char) 0x5DE5);

        RADICAL_KANGXI_TO_STD.put((char) 0x2F30, (char) 0x5DF1);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F31, (char) 0x5DFE);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F32, (char) 0x5E72);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F33, (char) 0x5E7A);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F34, (char) 0x5E7F);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F35, (char) 0x5EF4);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F36, (char) 0x5EFE);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F37, (char) 0x5F0B);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F38, (char) 0x5F13);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F39, (char) 0x5F50);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F3A, (char) 0x5F61);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F3B, (char) 0x5F73);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F3C, (char) 0x5FC3);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F3D, (char) 0x6208);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F3E, (char) 0x6236);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F3F, (char) 0x624B);

        RADICAL_KANGXI_TO_STD.put((char) 0x2F40, (char) 0x652F);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F41, (char) 0x6534);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F42, (char) 0x6587);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F43, (char) 0x6597);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F44, (char) 0x65A4);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F45, (char) 0x65B9);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F46, (char) 0x65E0);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F47, (char) 0x65E5);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F48, (char) 0x66F0);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F49, (char) 0x6708);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F4A, (char) 0x6728);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F4B, (char) 0x6B20);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F4C, (char) 0x6B62);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F4D, (char) 0x6B79);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F4E, (char) 0x6BB3);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F4F, (char) 0x6BCB);

        RADICAL_KANGXI_TO_STD.put((char) 0x2F50, (char) 0x6BD4);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F51, (char) 0x6BDB);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F52, (char) 0x6C0F);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F53, (char) 0x6C14);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F54, (char) 0x6C34);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F55, (char) 0x706B);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F56, (char) 0x722A);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F57, (char) 0x7236);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F58, (char) 0x723B);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F59, (char) 0x723F);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F5A, (char) 0x7247);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F5B, (char) 0x7259);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F5C, (char) 0x725B);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F5D, (char) 0x72AC);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F5E, (char) 0x7384);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F5F, (char) 0x7389);

        RADICAL_KANGXI_TO_STD.put((char) 0x2F60, (char) 0x74DC);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F61, (char) 0x74E6);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F62, (char) 0x7518);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F63, (char) 0x751F);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F64, (char) 0x7528);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F65, (char) 0x7530);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F66, (char) 0x758B);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F67, (char) 0x7592);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F68, (char) 0x7676);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F69, (char) 0x767D);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F6A, (char) 0x76AE);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F6B, (char) 0x76BF);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F6C, (char) 0x76EE);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F6D, (char) 0x77DB);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F6E, (char) 0x77E2);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F6F, (char) 0x77F3);

        RADICAL_KANGXI_TO_STD.put((char) 0x2F70, (char) 0x793A);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F71, (char) 0x79B8);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F72, (char) 0x79BE);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F73, (char) 0x7A74);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F74, (char) 0x7ACB);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F75, (char) 0x7AF9);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F76, (char) 0x7C73);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F77, (char) 0x7CF8);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F78, (char) 0x7F36);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F79, (char) 0x7F51);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F7A, (char) 0x7F8A);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F7B, (char) 0x7FBD);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F7C, (char) 0x8001);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F7D, (char) 0x800C);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F7E, (char) 0x8012);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F7F, (char) 0x8033);

        RADICAL_KANGXI_TO_STD.put((char) 0x2F80, (char) 0x807F);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F81, (char) 0x8089);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F82, (char) 0x81E3);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F83, (char) 0x81EA);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F84, (char) 0x81F3);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F85, (char) 0x81FC);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F86, (char) 0x820C);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F87, (char) 0x821B);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F88, (char) 0x821F);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F89, (char) 0x826E);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F8A, (char) 0x8272);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F8B, (char) 0x8278);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F8C, (char) 0x864D);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F8D, (char) 0x866B);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F8E, (char) 0x8840);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F8F, (char) 0x884C);

        RADICAL_KANGXI_TO_STD.put((char) 0x2F90, (char) 0x8863);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F91, (char) 0x897E);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F92, (char) 0x898B);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F93, (char) 0x89D2);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F94, (char) 0x8A00);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F95, (char) 0x8C37);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F96, (char) 0x8C46);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F97, (char) 0x8C55);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F98, (char) 0x8C78);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F99, (char) 0x8C9D);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F9A, (char) 0x8D64);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F9B, (char) 0x8D70);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F9C, (char) 0x8DB3);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F9D, (char) 0x8EAB);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F9E, (char) 0x8ECA);
        RADICAL_KANGXI_TO_STD.put((char) 0x2F9F, (char) 0x8F9B);

        RADICAL_KANGXI_TO_STD.put((char) 0x2FA0, (char) 0x8FB0);
        RADICAL_KANGXI_TO_STD.put((char) 0x2FA1, (char) 0x8FB5);
        RADICAL_KANGXI_TO_STD.put((char) 0x2FA2, (char) 0x9091);
        RADICAL_KANGXI_TO_STD.put((char) 0x2FA3, (char) 0x9149);
        RADICAL_KANGXI_TO_STD.put((char) 0x2FA4, (char) 0x91C6);
        RADICAL_KANGXI_TO_STD.put((char) 0x2FA5, (char) 0x91CC);
        RADICAL_KANGXI_TO_STD.put((char) 0x2FA6, (char) 0x91D1);
        RADICAL_KANGXI_TO_STD.put((char) 0x2FA7, (char) 0x9577);
        RADICAL_KANGXI_TO_STD.put((char) 0x2FA8, (char) 0x9580);
        RADICAL_KANGXI_TO_STD.put((char) 0x2FA9, (char) 0x961C);
        RADICAL_KANGXI_TO_STD.put((char) 0x2FAA, (char) 0x96B6);
        RADICAL_KANGXI_TO_STD.put((char) 0x2FAB, (char) 0x96B9);
        RADICAL_KANGXI_TO_STD.put((char) 0x2FAC, (char) 0x96E8);
        RADICAL_KANGXI_TO_STD.put((char) 0x2FAD, (char) 0x9751);
        RADICAL_KANGXI_TO_STD.put((char) 0x2FAE, (char) 0x975E);
        RADICAL_KANGXI_TO_STD.put((char) 0x2FAF, (char) 0x9762);

        RADICAL_KANGXI_TO_STD.put((char) 0x2FB0, (char) 0x9769);
        RADICAL_KANGXI_TO_STD.put((char) 0x2FB1, (char) 0x97CB);
        RADICAL_KANGXI_TO_STD.put((char) 0x2FB2, (char) 0x97ED);
        RADICAL_KANGXI_TO_STD.put((char) 0x2FB3, (char) 0x97F3);
        RADICAL_KANGXI_TO_STD.put((char) 0x2FB4, (char) 0x9801);
        RADICAL_KANGXI_TO_STD.put((char) 0x2FB5, (char) 0x98A8);
        RADICAL_KANGXI_TO_STD.put((char) 0x2FB6, (char) 0x98DB);
        RADICAL_KANGXI_TO_STD.put((char) 0x2FB7, (char) 0x98DF);
        RADICAL_KANGXI_TO_STD.put((char) 0x2FB8, (char) 0x9996);
        RADICAL_KANGXI_TO_STD.put((char) 0x2FB9, (char) 0x9999);
        RADICAL_KANGXI_TO_STD.put((char) 0x2FBA, (char) 0x99AC);
        RADICAL_KANGXI_TO_STD.put((char) 0x2FBB, (char) 0x9AA8);
        RADICAL_KANGXI_TO_STD.put((char) 0x2FBC, (char) 0x9AD8);
        RADICAL_KANGXI_TO_STD.put((char) 0x2FBD, (char) 0x9ADF);
        RADICAL_KANGXI_TO_STD.put((char) 0x2FBE, (char) 0x9B25);
        RADICAL_KANGXI_TO_STD.put((char) 0x2FBF, (char) 0x9B2F);

        RADICAL_KANGXI_TO_STD.put((char) 0x2FC0, (char) 0x9B32);
        RADICAL_KANGXI_TO_STD.put((char) 0x2FC1, (char) 0x9B3C);
        RADICAL_KANGXI_TO_STD.put((char) 0x2FC2, (char) 0x9B5A);
        RADICAL_KANGXI_TO_STD.put((char) 0x2FC3, (char) 0x9CE5);
        RADICAL_KANGXI_TO_STD.put((char) 0x2FC4, (char) 0x9E75);
        RADICAL_KANGXI_TO_STD.put((char) 0x2FC5, (char) 0x9E7F);
        RADICAL_KANGXI_TO_STD.put((char) 0x2FC6, (char) 0x9EA5);
        RADICAL_KANGXI_TO_STD.put((char) 0x2FC7, (char) 0x9EBB);
        RADICAL_KANGXI_TO_STD.put((char) 0x2FC8, (char) 0x9EC3);
        RADICAL_KANGXI_TO_STD.put((char) 0x2FC9, (char) 0x9ECD);
        RADICAL_KANGXI_TO_STD.put((char) 0x2FCA, (char) 0x9ED1);
        RADICAL_KANGXI_TO_STD.put((char) 0x2FCB, (char) 0x9EF9);
        RADICAL_KANGXI_TO_STD.put((char) 0x2FCC, (char) 0x9EFD);
        RADICAL_KANGXI_TO_STD.put((char) 0x2FCD, (char) 0x9F0E);
        RADICAL_KANGXI_TO_STD.put((char) 0x2FCE, (char) 0x9F13);
        RADICAL_KANGXI_TO_STD.put((char) 0x2FCF, (char) 0x9F20);

        RADICAL_KANGXI_TO_STD.put((char) 0x2FD0, (char) 0x9F3B);
        RADICAL_KANGXI_TO_STD.put((char) 0x2FD1, (char) 0x9F4A);
        RADICAL_KANGXI_TO_STD.put((char) 0x2FD2, (char) 0x9F52);
        RADICAL_KANGXI_TO_STD.put((char) 0x2FD3, (char) 0x9F8D);
        RADICAL_KANGXI_TO_STD.put((char) 0x2FD4, (char) 0x9F9C);
        RADICAL_KANGXI_TO_STD.put((char) 0x2FD5, (char) 0x9FA0);

        RADICAL_KANGXI_TO_STD.put((char) 0X2ECB, (char) 0x8F66);
        RADICAL_KANGXI_TO_STD.put((char) 0X2ED3, (char) 0x957F);
        RADICAL_KANGXI_TO_STD.put((char) 0X2ED4, (char) 0x95E8);
        RADICAL_KANGXI_TO_STD.put((char) 0X2EE2, (char) 0x9A6C);
        RADICAL_KANGXI_TO_STD.put((char) 0X2EEC, (char) 0x9F50);
    }

    private static final Character[] CHARACTERS = new Character[Character.MAX_VALUE];

    public static Character getCached(char input) {
        if (CHARACTERS[input] == null) {
            CHARACTERS[input] = Character.valueOf(input);
        }

        return CHARACTERS[input];
    }

    /**
     * 标点符号表字段
     */
    private static final Set<Character> PUNCTUATIONS = new HashSet<Character>();

    static {
        for (char _char : "！@#￥%……&×（），。；‘’“”：【】『』、《》？·～,./;':\"\\[]{}`~!@#$%^&*()_+-=| \t\b\r\n".toCharArray()) {
            PUNCTUATIONS.add(_char);
        }
    }

    /**
     * 判断是否是标点符号
     *
     * @param input
     * @return
     */
    public static boolean isPunctuation(char input) {
        return PUNCTUATIONS.contains(input);
    }

    /**
     * 左引号／左括号表字段
     */
    private static final Set<Character> BRACKETS_HEAD = new HashSet<Character>();

    static {
        for (char _char : "‘“【『《[{(".toCharArray()) {
            BRACKETS_HEAD.add(_char);
        }
    }

    /**
     * 判断是否是左引号／左括号
     *
     * @param input
     * @return
     */
    public static boolean isBracketHead(char input) {
        return BRACKETS_HEAD.contains(input);
    }

    /**
     * 右引号／右括号表字段
     */
    private static final Set<Character> BRACKETS_TAIL = new HashSet<Character>();

    static {
        for (char _char : "）】』》]})".toCharArray()) {
            BRACKETS_TAIL.add(_char);
        }
    }

    /**
     * 判断是否是右引号／右括号
     *
     * @param input
     * @return
     */
    public static boolean isBracketTail(char input) {
        return BRACKETS_TAIL.contains(input);
    }

    /**
     * 判断是否是空白符号
     *
     * @param input
     * @return
     */
    public static boolean isSpace(char input) {
        return input == 8 || input == 9 || input == 10 || input == 13 || input == 32 || input == 160;
    }

    /**
     * 判断是否是英文字母
     *
     * @param input
     * @return
     */
    public static boolean isLetter(char input) {
        return (input >= 'a' && input <= 'z') || (input >= 'A' && input <= 'Z');
    }

    /**
     * 判断是否是数字
     *
     * @param input
     * @return
     */
    public static boolean isNumber(char input) {
        return input >= '0' && input <= '9';
    }

    /**
     * 判断是否是中日韩统一表意文字
     *
     * @param input
     * @return
     */
    public static boolean isCJK(char input) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(input);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
            || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
            || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
            || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
            || ub == Character.UnicodeBlock.HANGUL_SYLLABLES
            || ub == Character.UnicodeBlock.HANGUL_JAMO
            || ub == Character.UnicodeBlock.HANGUL_COMPATIBILITY_JAMO
            || ub == Character.UnicodeBlock.HIRAGANA //平假名
            || ub == Character.UnicodeBlock.KATAKANA //片假名
            || ub == Character.UnicodeBlock.KATAKANA_PHONETIC_EXTENSIONS
                ) {
            return true;
        }

        return false;
    }

    /**
     * 转换为半角符号
     *
     * @param input
     * @return
     */
    public static char toHalfWidth(char input) {
        if (input == 12288) {
            input = (char) 32;
        } else if (input > 65280 && input < 65375) {
            input = (char) (input - 65248);
        }

        return input;
    }

    /**
     * 转换为全角符号
     *
     * @param input
     * @return
     */
    public static char toFullWidth(char input) {
        if (input == 32) {
            input = (char) 12288;
        } else if (input > 32 && input < 127) {
            input = (char) (input + 65248);
        }

        return input;
    }

    /**
     * 转换为小写字母
     *
     * @param input
     * @return
     */
    public static char toLowerCase(char input) {
        if (input >= 'A' && input <= 'Z') {
            input += 32;
        }

        return input;
    }

    /**
     * 转换为大写字母
     *
     * @param input
     * @return
     */
    public static char toUpperCase(char input) {
        if (input >= 'a' && input <= 'z') {
            input -= 32;
        }

        return input;
    }

    /**
     * big5转gbk
     * @param word
     * @return
     */
    public static String big5ToGbk(String word) {
        if (StringUtil.isNotEmpty(word)) {
            StringBuilder buff = new StringBuilder(word.length());
            for (char c : word.toCharArray()) {
                if (CharacterNatures.isChinese(c)) {
                    Character _c = Big5GBKMap.BIG5_TO_GBK.get(c);
                    if (_c != null) {
                        c = _c;
                    }
                }
                buff.append(c);
            }
            return buff.toString();
        }
        return word;
    }

    /**
     * gbk转big5
     * @param word
     * @return
     */
    public static String gbkTobig5(String word) {
        if (StringUtil.isNotEmpty(word)) {
            StringBuilder buff = new StringBuilder(word.length());
            for (char c : word.toCharArray()) {
                if (CharacterNatures.isChinese(c)) {
                    Character _c = Big5GBKMap.GBK_TO_BIG5.get(c);
                    if (_c != null) {
                        c = _c;
                    }
                }
                buff.append(c);
            }
            return buff.toString();
        }
        return word;
    }

}
