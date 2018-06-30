package database.kotlin.flow9.net.spbasic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Preference based on File System not on XML file.
 * Ready() to prepare for input
 * Commit() to write
 * Read() to read
 */
public class TextPref {

    private String mPath;
    private StringBuilder mBuf;
    public static final String HEADER = "__Text Preference File__\n";

    public TextPref(String path) throws IOException {
        mPath = path;
        File file = new File(mPath);
        if (!file.exists()) {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(HEADER.getBytes());
            fos.close();
        }
    }

    public void Reset() {
        File file = new File(mPath);
        if (file.exists()) {
            file.delete();
        }
    }

    public boolean Ready() {
        try {
            FileInputStream fis = new FileInputStream(mPath);
            int avail = fis.available();
            byte[] data = new byte[avail];
            while (fis.read(data) != -1) ;
            fis.close();
            mBuf = new StringBuilder(avail);
            mBuf.append(new String(data));
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public boolean CommitWrite() {
        File file = new File(mPath);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(mBuf.toString().getBytes());
            fos.close();
        } catch (IOException e) {
            return false;
        }
        mBuf = null;
        return true;
    }

    public void EndReady() {
        mBuf = null;
    }

    private int FindIdx(String name) {
        String key = "__" + name + "=";
        int idx = mBuf.indexOf(key);
        if (idx == -1) {
            return -1;
        } else {
            return idx + key.length();
        }
    }

    public void WriteString(String name, String value) {
        int idx = FindIdx(name);
        if (idx == -1) {
            mBuf.append("__");
            mBuf.append(name);
            mBuf.append("=");
            mBuf.append(value);
            mBuf.append("\n");
        } else {
            int end = mBuf.indexOf("\n", idx);
            mBuf.delete(idx, end);
            mBuf.insert(idx, value);
        }
    }

    public String ReadString(String name, String def) {
        int idx = FindIdx(name);
        if (idx == -1) {
            return def;
        } else {
            int end = mBuf.indexOf("\n", idx);
            return mBuf.substring(idx, end);
        }
    }

    public void WriteInt(String name, int value) {
        WriteString(name, Integer.toString(value));
    }

    public int ReadInt(String name, int def) {
        String s = ReadString(name, "__none");
        if (s.equals("__none")) {
            return def;
        }
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return def;
        }
    }

    public void WriteLong(String name, long value) {
        WriteString(name, Long.toString(value));
    }

    public long ReadLong(String name, long def) {
        String s = ReadString(name, "__none");
        if (s.equals("__none")) {
            return def;
        }
        try {
            return Long.parseLong(s);
        } catch (NumberFormatException e) {
            return def;
        }
    }

    public void WriteBoolean(String name, boolean value) {
        WriteString(name, value?"1":"0");
    }

    public boolean ReadBoolean(String name, boolean def) {
        String s = ReadString(name, "__none");
        if (s.equals("__none")) {
            return def;
        }
        try {
            return s.equals("1") ? true : false;
        } catch (NumberFormatException e) {
            return def;
        }
    }

    public void WriteFloat(String name, float value) {
        WriteString(name, Float.toString(value));
    }

    public float ReadFloat(String name, float def) {
        String s = ReadString(name, "__none");
        if (s.equals("__none")) {
            return def;
        }
        try {
            return Float.parseFloat(s);
        } catch (NumberFormatException e) {
            return def;
        }
    }

    public void BulkwriteReady(int length) {
        mBuf = new StringBuilder(length);
        mBuf.append(HEADER);
        mBuf.append("\n");
    }

    public void BulkWrite(String name, String value) {
        mBuf.append("__");
        mBuf.append(name);
        mBuf.append("=");
        mBuf.append(value);
        mBuf.append("\n");
    }

    public void DeleteKey(String name) {
        int idx = FindIdx(name);
        if (idx != -1) {
            int end = mBuf.indexOf("\n", idx);
            mBuf.delete(idx - (name.length() + 3), end +1);
        }
    }

}
