package com.shreyas.IOandWorkingWithFiles;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class DataOutputInputStream {
    public static void main(String[] args) {
        writeData(Path.of("src/com/shreyas/IOandWorkingWithFiles/data.dat"));
        readData(Path.of("src/com/shreyas/IOandWorkingWithFiles/data.dat"));
    }

    public static void writeData(Path dataFile) {
        try (DataOutputStream dataOutputStream =
                     new DataOutputStream(
                             new BufferedOutputStream(
                                     new FileOutputStream(dataFile.toFile())
                             )
                     )) {
            int intValue = 42;
            long longValue = 1234567890L;
            float floatValue = 3.14f;
            double doubleValue = 3.14159265358979323846;
            boolean booleanValue = true;
            char charValue = 'A';
            String stringValue = "Hello, World!";

            long position = 0;
            dataOutputStream.writeInt(intValue);
            System.out.println("WriteInt :" + (dataOutputStream.size() - position));
            position = dataOutputStream.size();

            dataOutputStream.writeLong(longValue);
            System.out.println("WriteLong :" + (dataOutputStream.size() - position));
            position = dataOutputStream.size();

            dataOutputStream.writeFloat(floatValue);
            System.out.println("WriteFloat :" + (dataOutputStream.size() - position));
            position = dataOutputStream.size();

            dataOutputStream.writeDouble(doubleValue);
            System.out.println("WriteDouble :" + (dataOutputStream.size() - position));
            position = dataOutputStream.size();

            dataOutputStream.writeBoolean(booleanValue);
            System.out.println("WriteBoolean :" + (dataOutputStream.size() - position));
            position = dataOutputStream.size();

            dataOutputStream.writeChar(charValue);
            System.out.println("WriteChar :" + (dataOutputStream.size() - position));
            position = dataOutputStream.size();

            dataOutputStream.writeUTF(stringValue);
            System.out.println("WriteUTF :" + (dataOutputStream.size() - position));
            position = dataOutputStream.size();
        } catch (IOException e) {
            throw new RuntimeException("Error writing data to file", e);
        }
    }

    private static void readData(Path dataFile) {
        try (DataInputStream dataInputStream =
                     new DataInputStream(
                             Files.newInputStream(dataFile)
                     )
        ) {
            int intValue = dataInputStream.readInt();
            System.out.println("ReadInt: " + intValue);

            long longValue = dataInputStream.readLong();
            System.out.println("ReadLong: " + longValue);

            float floatValue = dataInputStream.readFloat();
            System.out.println("ReadFloat: " + floatValue);

            double doubleValue = dataInputStream.readDouble();
            System.out.println("ReadDouble: " + doubleValue);

            boolean booleanValue = dataInputStream.readBoolean();
            System.out.println("ReadBoolean: " + booleanValue);

            char charValue = dataInputStream.readChar();
            System.out.println("ReadChar: " + charValue);

            String stringValue = dataInputStream.readUTF();
            System.out.println("ReadUTF: " + stringValue);
        } catch (IOException e) {
            throw new RuntimeException("Error reading data from file", e);
        }
    }
}
