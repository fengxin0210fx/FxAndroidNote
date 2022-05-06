package com.ahyq.utilslibrary;

public class Globle {
    static public final int SIGNAL_INPUT_iSV3101USB = 0;
    static public final int SIGNAL_INPUT_MIC = 1;
    static public final int SIGNAL_INPUT_AWA6292 = 2;
    static public final String FIFO_NAME_INTERGRAL = "INT";
    //screen PORTRAIT or LANDSCAPE
//	public static final int SCREEN_DIRECTION_PORTRAIT = 0;
//	public static final int SCREEN_DIRECTION_LANDSCAPE = 1;
//	public static int screenDirection = SCREEN_DIRECTION_PORTRAIT;
    static public final String FIFO_NAME_STATISTICS = "STA";
    static public final String FIFO_NAME_CPB = "CPB";
    static public final String FIFO_NAME_FFT = "FFT";
    static public final String FIFO_NAME_REC1 = "REC1";//第1通道录音
    static public final String FIFO_NAME_REC2 = "REC2";//第2通道录音
    static public final String FIFO_NAME_REC3 = "REC3";//第3通道录音
    static public final String FIFO_NAME_REC4 = "REC4";//第4通道录音
    static public final String FIFO_NAME_REC5 = "REC5";//第5通道录音
    static public final String FIFO_NAME_REC6 = "REC6";//第6通道录音
    static public final String FIFO_NAME_REC7 = "REC7";//第7通道录音
    static public final String FIFO_NAME_REC8 = "REC8";//第8通道录音
    static public final String FIFO_NAME_REC9 = "REC9";//第9通道录音
    static public final String FIFO_NAME_REC10 = "REC10";//第10通道录音
    //频率计权
    static public final int A = 0;
    static public final int C = 1;
    static public final int Z = 2;
    //时间计权
    static public final int F = 0;
    static public final int S = 1;
    static public final int I = 2;
    static public final int RECORD_IDLE = 0;
    static public final int RECORD_READY = 1;
    static public final int RECORD_RUNNING = 2;
    static public final int RECORD_PAUSE = 3;
    //窗类型
    static public final int REC = 0; //矩形
    static public final int TAP = 1;  // 梯形
    static public final int TRI = 2; //三角

    //平均类型

//    static public final int EXP_AVG = 0;
//    static public final int LINEAR_AVG = 1;
    static public final int HAN = 3; //汉宁
    static public final int HAM = 4; //汉明
    static public final int BLA = 5; //布莱克曼
    static public final int FLA = 6;//平顶
    //Mic Mode
    static public final int MICMODE_DEGREE_0 = 0;
    static public final int MICMODE_DEGREE_90 = 1;
    static public final int MICMODE_DIFFUSION = 2;
    public static final int MEASUR_READY = 0;
    public static final int MEASUR_START = 1;
    public static final int MEASUR_PAUSE = 2;
    public static final int MEASUR_RESUME = 3;
    static public int fs = 48000;    //采样率
    static public int channelTotal = 1;    //通道总数
    //
//
//
    static public int RECVDATAMODEL = SIGNAL_INPUT_AWA6292;
    static public int MIC_MODE = MICMODE_DEGREE_0;
    //for 20dB modify
    public static float modifyFor20dBA = 0;
    static float[] Oct3XCoordinate = new float[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0.5f, 0.63f, 0.8f, 1, 1.25f, 1.6f, 2, 2.5f, 3.15f, 4, 5, 6.3f, 8, 10, 12.5f, 16, 20, 25, 31.5f, 40, 50, 63, 80, 100, 125, 160, 200, 250, 315, 400, 500, 630, 800, 1000, 1250, 1600, 2000, 2500, 3150, 4000, 5000, 6300, 8000, 10000, 12500, 16000, 20000, 25000, 31500, 40000};  //64
    static float[] Oct1XCoordinate = new float[]{0.016f, 0.0315f, 0.063f, 0.125f, 0.25f, 0.5f, 1, 2, 4, 8, 16, 31.5f, 63, 125, 250, 500, 1000, 2000, 4000, 8000, 16000, 31500};
    static float[] Oct6XCoordinate = new float[]{18.8f, 21.1f, 23.7f, 26.6f, 29.9f, 33.5f, 37.6f, 42.2f, 47.3f, 53, 60, 67, 75, 84, 94, 106, 119, 133, 150, 168, 188, 211, 237,
            266, 299, 335, 376, 422, 473, 530, 600, 670, 750, 840, 940, 1060, 1190, 1330, 1500, 1680, 1880, 2110, 2370, 2660, 2990, 3350, 3760, 4220, 4730, 5300, 6000, 6700, 7500, 8400,
            9400, 10600, 11900, 13300, 15000, 16800, 18800}; //1060
    static float[] Oct12XCoordinate = new float[]{20.5f, 21.8f, 23, 24.4f, 25.9f, 27.4f, 29, 30.7f, 32.5f, 34.5f, 36.5f, 38.7f, 41, 43.4f, 46, 48.7f, 52, 55, 58, 61,
            65, 69, 73, 77, 82, 87, 92, 97, 103, 109, 115, 122, 130, 137, 145, 154, 163, 173, 183, 194,
            205, 218, 230, 244, 259, 274, 290, 307, 325, 345, 365, 387, 410, 434, 460, 487, 520, 550, 580, 610,
            650, 690, 730, 770, 820, 870, 920, 970, 1030, 1090, 1150, 1220, 1300, 1370, 1450, 1540, 1630, 1730, 1830, 1940,
            2050, 2180, 2300, 2440, 2590, 2740, 2900, 3070, 3250, 3450, 3650, 3870, 4100, 4340, 4600, 4870, 5200, 5500, 5800, 6100,
            6500, 6900, 7300, 7700, 8200, 8700, 9200, 9700, 10300, 10900, 11500, 12200, 13000, 13700, 14500, 15400, 16300, 17300, 18300, 19400,
            20500};//1030
    private static byte[] bytes = new byte[4];
    private static byte[] shortBytes = new byte[2];
    final String[] winStr = {"Rec", "Tap", "Tri", "Han", "Ham", "Bla", "Fla"};

    /**
     * @param freqWeiTypeIn 频率计权
     * @param timeWeiTypeIn 时间计权
     * @return 返回计权的字符串 如 AF  CS ZI 等 9 种组合
     */
    public synchronized static String getHzWeiStr(int freqWeiTypeIn, int timeWeiTypeIn) {
        String s = "";
        if (freqWeiTypeIn == A) {
            s = s + "A";
        }
        if (freqWeiTypeIn == C) {
            s = s + "C";
        }
        if (freqWeiTypeIn == Z) {
            s = s + "Z";
        }

        if (timeWeiTypeIn == F) {
            s = s + "F";
        }
        if (timeWeiTypeIn == S) {
            s = s + "S";
        }
        if (timeWeiTypeIn == I) {
            s = s + "I";
        }
        return s;
    }

    //高字节在数组前
    public static int bytesToIntH(byte b[]) {
        return b[3] & 0xff | (b[2] & 0xff) << 8 | (b[1] & 0xff) << 16 | (b[0] & 0xff) << 24;
    }

    //低字节在数组前
    public static int bytesToIntL(byte b[]) {
        return b[0] & 0xff | (b[1] & 0xff) << 8 | (b[2] & 0xff) << 16 | (b[3] & 0xff) << 24;
    }

    //int的髙字节在数组前
    public static byte[] intToBytesH(int in) {
        byte[] b = new byte[4];
        b[3] = (byte) (in & 0xff);
        b[2] = (byte) ((in >> 8) & 0xff);
        b[1] = (byte) ((in >> 16) & 0xff);
        b[0] = (byte) ((in >> 24) & 0xff);

        return b;
    }

    //int的低字节在数组前
    public static byte[] intToBytesL(int in) {
        byte[] b = new byte[4];
        b[0] = (byte) (in & 0xff);
        b[1] = (byte) ((in >> 8) & 0xff);
        b[2] = (byte) ((in >> 16) & 0xff);
        b[3] = (byte) ((in >> 24) & 0xff);

        return b;
    }

    public static int bytesToShort(byte b[]) {
        return b[1] & 0xff | (b[0] & 0xff) << 8;
    }

    public static byte[] shourToBytes(short in) {
        byte[] b = new byte[2];
        b[1] = (byte) (in & 0xff);
        b[0] = (byte) ((in >> 8) & 0xff);

        return b;
    }

    public static byte[] recordInt2Bytes(int integer) {

        bytes[3] = (byte) (integer >> 24);
        bytes[2] = (byte) (integer >> 16);
        bytes[1] = (byte) (integer >> 8);
        bytes[0] = (byte) integer;

        return bytes;
    }

    public static byte[] recordShort2Bytes(int shortdata) {
        shortBytes[1] = (byte) (shortdata >> 8);
        shortBytes[0] = (byte) shortdata;

        return shortBytes;
    }

}
