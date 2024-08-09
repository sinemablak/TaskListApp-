package com.example;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TaskListApp extends Application {
    private TaskManager taskManager = new TaskManager();
    private ObservableList<Task> taskList = FXCollections.observableArrayList();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Görev Listesi Uygulaması");

        ListView<Task> listView = new ListView<>(taskList);
        listView.setPrefHeight(300);

        TextField taskInput = new TextField();
        taskInput.setPromptText("Görev başlığı");

        Button addButton = new Button("Ekle");
        addButton.setOnAction(e -> addTask(taskInput.getText()));

        Button removeButton = new Button("Sil");
        removeButton.setOnAction(e -> removeTask(listView.getSelectionModel().getSelectedItem()));

        Button completeButton = new Button("Tamamla");
        completeButton.setOnAction(e -> markAsCompleted(listView.getSelectionModel().getSelectedItem()));

        VBox vbox = new VBox(10, taskInput, addButton, removeButton, completeButton, listView);
        vbox.setPadding(new Insets(10));

        Scene scene = new Scene(vbox, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void addTask(String title) {
        if (!title.trim().isEmpty()) {
            Task newTask = new Task(title);
            taskManager.addTask(newTask);
            taskList.add(newTask);
        }
    }

    private void removeTask(Task task) {
        if (task != null) {
            taskManager.removeTask(task);
            taskList.remove(task);
        }
    }

    private void markAsCompleted(Task task) {
        if (task != null) {
            task.setCompleted(!task.isCompleted());
            taskList.set(taskList.indexOf(task), task); // Update list view
        }
    }
}
