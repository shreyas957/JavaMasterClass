package com.shreyas.IOandWorkingWithFiles.serialization;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class SerializationExample {
    public static void main(String[] args) {
        Player shreyas = new Player("Shreyas", 100_000, new ArrayList<>(List.of("Sword", "Shield", "Bow")), "I am Batman");
        System.out.println(shreyas);

        Path dataFile = Path.of("src/com/shreyas/IOandWorkingWithFiles/serialization/player.dat");
//        writeObject(dataFile, shreyas);
        Player reconstructedShreyas = readObject(dataFile);
        System.out.println("Reconstructed : " + reconstructedShreyas);

        Player shreyas2 = new Player("Shreyas", 100_000, new ArrayList<>(List.of("Sword", "Shield", "Bow")), "I am IRONMAN");
        System.out.println(shreyas2);
        writeObject(dataFile, shreyas2);
        Player reconstructedShreyas2 = readObject(dataFile);
        System.out.println("Reconstructed : " + reconstructedShreyas2);

    }

    private static void writeObject(Path dataFile, Player player) {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                Files.newOutputStream(dataFile)
        )) {
            objectOutputStream.writeObject(player);
        } catch (IOException e) {
            throw new RuntimeException("Error writing data to file", e);
        }
    }

    private static Player readObject(Path dataFile) {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(
                Files.newInputStream(dataFile)
        )) {
            return (Player) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Error reading data from file", e);
        }
    }
}

class Player implements Serializable {

    @Serial
    private final static long serialVersionUID = 1234567L;
    private String name;
    private int topScore;
    private ArrayList<String> collectedWeapons;
    private final transient String secret;

    public Player(String name, int topScore, ArrayList<String> collectedWeapons, String secret) {
        this.name = name;
        this.topScore = topScore;
        this.collectedWeapons = collectedWeapons;
        this.secret = secret;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTopScore() {
        return topScore;
    }

    public void setTopScore(int topScore) {
        this.topScore = topScore;
    }

    public ArrayList<String> getCollectedWeapons() {
        return collectedWeapons;
    }

    public void setCollectedWeapons(ArrayList<String> collectedWeapons) {
        this.collectedWeapons = collectedWeapons;
    }

    public String getSecret() {
        return secret;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", topScore=" + topScore +
                ", collectedWeapons=" + collectedWeapons +
                ", secret='" + secret + '\'' +
                '}';
    }

    @Serial
    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();     // Calling default serialization process
        topScore = (topScore == 100_000) ? 100 : topScore;
    }

    @Serial
    private void writeObject(ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();     // Calling default serialization process
        // I can change the order of the write operations
    }
}
