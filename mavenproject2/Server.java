/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.mycompany.mavenproject2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author HP
 */


public class Server {

    public static void main(String[] args) {
        try {
            // Création d'un ServerSocket qui écoute sur le port 8888
            ServerSocket serverSocket = new ServerSocket();
            System.out.println("Serveur en attente de connexions...");

            while (true) {
                try ( // Attente d'une connexion cliente
                        Socket clientSocket = serverSocket.accept()) {
                    System.out.println("Connexion acceptée depuis " + clientSocket.getInetAddress());
                    
                    PrintWriter printWriter;
                    try ( // Lecture des données envoyées par le client
                            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
                        String pseudo = bufferedReader.readLine();
                        String password = bufferedReader.readLine();
                        // Traitement des données (dans cet exemple, simplement affichage)
                        System.out.println("Pseudo: " + pseudo);
                        System.out.println("Mot de passe: " + password);
                        // Envoi d'un accusé de réception au client
                        printWriter = new PrintWriter(clientSocket.getOutputStream(), true);
                        printWriter.println("SUCCESS");
                        // Fermeture des flux et de la connexion
                    }
                    printWriter.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
