package com.zmr.common.core.utils;

import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.pqc.legacy.math.linearalgebra.ByteUtils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.Security;

/**
 * @Author: Aizmr
 * @CreateTime: 2025-11-10
 * @Description:
 */
@Slf4j
public class SM4Utils {
    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    private static final String ENCODING = "UTF-8";
    private static final String ALGORITHM_NAME = "SM4";
    // 加密算法/分组加密模式/分组填充方式
    // PKCS5Padding-以8个字节为一组进行分组加密
    // 定义分组加密模式使用：PKCS5Padding
    private static final String ALGORITHM_NAME_CBC_PADDING = "SM4/CBC/PKCS5Padding";


    /**
     * 生成CBC暗号
     *
     * @param algorithmName 算法名称
     * @param mode          模式
     * @param key
     * @return
     * @throws Exception
     * @explain CBC模式（密文分组链接方式）
     */
    public static Cipher generateCbcCipher(String algorithmName, int mode, byte[] key) throws Exception {
        Cipher cipher = Cipher.getInstance(algorithmName, BouncyCastleProvider.PROVIDER_NAME);
        Key sm4Key = new SecretKeySpec(key, ALGORITHM_NAME);
        //初始向量
        IvParameterSpec iv = new IvParameterSpec(key);
        cipher.init(mode, sm4Key, iv);
        return cipher;
    }

    /**
     * sm4加密
     *
     * @param hexKey   16进制密钥（忽略大小写）
     * @param paramStr 待加密字符串
     * @return 返回16进制的加密字符串
     * @throws Exception
     * @explain 加密模式：CBC
     * 密文长度不固定，会随着被加密字符串长度的变化而变化
     */
    public static String encryptCbc(String hexKey, String paramStr) throws Exception {
        log.info("加密前：{}", paramStr);
        System.out.println("加密前：" + paramStr);
        String cipherText = "";
        // 16进制字符串--&gt;byte[]
        byte[] keyData = ByteUtils.fromHexString(hexKey);
        // String--&gt;byte[]
        byte[] srcData = paramStr.getBytes(ENCODING);
        // 加密后的数组
        byte[] cipherArray = encrypt_Cbc_Padding(keyData, srcData);
        // byte[]--&gt;hexString
        cipherText = ByteUtils.toHexString(cipherArray);
        return cipherText;
    }

    /**
     * 加密模式之Cbc
     *
     * @param key
     * @param data
     * @return
     * @throws Exception
     * @explain
     */
    public static byte[] encrypt_Cbc_Padding(byte[] key, byte[] data) throws Exception {
        Cipher cipher = generateCbcCipher(ALGORITHM_NAME_CBC_PADDING, Cipher.ENCRYPT_MODE, key);
        return cipher.doFinal(data);
    }

    /**
     * sm4解密
     *
     * @param hexKey     16进制密钥
     * @param cipherText 16进制的加密字符串（忽略大小写）
     * @return 解密后的字符串
     * @throws Exception
     * @explain 解密模式：采用CBC
     */
    public static String decryptCbc(String hexKey, String cipherText) throws Exception {
        // 用于接收解密后的字符串
        String decryptStr = "";
        // hexString--&gt;byte[]
        byte[] keyData = ByteUtils.fromHexString(hexKey);
        // hexString--&gt;byte[]
        byte[] cipherData = ByteUtils.fromHexString(cipherText);
        // 解密
        byte[] srcData = decrypt_Cbc_Padding(keyData, cipherData);
        // byte[]--&gt;String
        decryptStr = new String(srcData, ENCODING);
        return decryptStr;
    }

    /**
     * 解密
     *
     * @param key
     * @param cipherText
     * @return
     * @throws Exception
     * @explain
     */
    public static byte[] decrypt_Cbc_Padding(byte[] key, byte[] cipherText) throws Exception {
        Cipher cipher = generateCbcCipher(ALGORITHM_NAME_CBC_PADDING, Cipher.DECRYPT_MODE, key);
        return cipher.doFinal(cipherText);
    }


    public static void main(String[] args) throws Exception {
        String key = "f1c8f9667a3654883296f87be291722d";
        String cipher = "74386b823953c765f090fd8993b42bfeecc6015e80adf90f6ec9d66c3d266cf603e97c05ac054265e94e6377f78b2e2a72327c24168b80ec9afe6a9117112a698e46aaf9a08d12be82312f0840dcf5e3c551e3ac909489cf3b8d9849fcfa56a4d150af0a4f9ddc7c018371078298b4ed1ba302fb1020d6777c8171a0371ae14bf4ae7995f45ad3599b95f41db5bb991f";

        String str = "[{\"alarmTotal\": 100,\"alarmHigh\": 20,\"alarmMedium\": 50,\"alarmLow\": 30,\"netAlarmTotal\": 50,\"netAlarmHigh\": 30,\"netAlarmMedium\": 10,\"netAlarmLow\": 20,\"harmAlarmToatl\": 30,\"harmAlarmHigh\": 10,\"harmAlarmMedium\": 10,\"harmAlarmLow\": 10,\"destrAlarmTotal\": 10,\"destrAlarmHigh\": 5,\"destrAlarmMedium\": 5,\"destrAlarmLow\": 5,\"otherAlarmTotal\": 10,\"otherAlarmHigh\": 5,\"otherAlarmMedium\": 5,\"otherAlarmLow\": 5,\"alarmDate\": \"2021-12-21\",\"origNodeCode\": \"130000\"}]";
        String encryptCbc = SM4Utils.encryptCbc(key, str);
        System.out.println(encryptCbc);
        String end = SM4Utils.decryptCbc(key, cipher);
        System.out.println(end);
    }
}
