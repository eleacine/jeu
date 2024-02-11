package Shooter.factory;
import Shooter.model.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;


public class EnemyLevelLoader {
    private Map<Integer, List<PersonnageFactory>> levelEnemyFactories;

    public EnemyLevelLoader() {
        levelEnemyFactories = new HashMap<>();
    }

    public void loadLevelEnemies(String filePath) { //filepath du document txt avec les infos de chq niveau
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) { //au cas oú on fait une erreur de syntaxe dans le txt 
                    int level = Integer.parseInt(parts[0].trim());
                    String[] enemyTypes = parts[1].split(",");
                    List<PersonnageFactory> factories = createFactoriesForEnemyTypes(enemyTypes);
                    levelEnemyFactories.put(level, factories);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Personnage> createEnemiesForLevel(int level) {
        List<PersonnageFactory> factories = levelEnemyFactories.get(level);
        List<Personnage> enemies = new ArrayList<>();
    
        if (factories != null) {
            //System.out.println("Number of factories for level " + level + ": " + factories.size());
            for (PersonnageFactory factory : factories) {
                Personnage enemy = factory.createPersonnage();
                //System.out.println("Created enemy: " + enemy);
                enemies.add(enemy);
            }
        }
    
        return enemies;
    }
    

    private List<PersonnageFactory> createFactoriesForEnemyTypes(String[] enemyTypes) { //parcoure liste d'enemies
        List<PersonnageFactory> factories = new ArrayList<>();

        for (String enemyType : enemyTypes) {
            enemyType = enemyType.trim().replace(" ", "");
            factories.add(createFactoryForEnemyType(enemyType));
        }

        return factories;
    }

    private PersonnageFactory createFactoryForEnemyType(String enemyType) {//ajoute factory pour l'enemi specifique 
        switch (enemyType) {
            case "Enemy1":
                return new Enemy1Factory();
            case "Enemy2":
                return new Enemy2Factory();
            default:
                throw new IllegalArgumentException("Unknown enemy type: " + enemyType);
        }
    }
}
