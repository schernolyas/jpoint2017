/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.schernolyas.performance;

/**
 *
 * @author Сергей
 */
public interface TestProgram {
    
    void writeToStorage();
    
    void readSequentially();
    
    void readRandomly();
    
}
