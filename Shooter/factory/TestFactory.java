// package Shooter.factory;

// import Shooter.model.*;

// import java.util.List;

public class TestFactory {
    public static void main(String[] args) {
        EnemyLevelLoader enemyLoader = new EnemyLevelLoader(2);
        enemyLoader.loadLevelEnemies("Shooter\\factory\\EnemiesForLevels.txt");
        List<Personnage> enemiesLevel1 = enemyLoader.createEnemiesForLevel();
        List<Personnage> enemiesLevel2 = enemyLoader.createEnemiesForLevel();
        
//         //Affichage du test 
//         for(Personnage enemy1 : enemiesLevel1){
//             if (enemy1 instanceof E1) {
//                 E1 e1Enemy1 = (E1) enemy1;
//                 System.out.println("Level 1 - Enemy 1 Power: " + e1Enemy1.power);
//             }
//             if (enemy1 instanceof E2) {
//                 E2 e2Enemy2 = (E2) enemy1;
//                 System.out.println("Level 1 - Enemy 2 Power: " + e2Enemy2.power);
//             }
//         }
//         for(Personnage enemy2 : enemiesLevel2) {
//             if (enemy2 instanceof E1) {
//                 E1 e1Enemy1 = (E1) enemy2;
//                 System.out.println("Level 2 - Enemy 1 Power: " + e1Enemy1.power);
//             }
//             if (enemy2 instanceof E2) {
//                 E2 e2Enemy2 = (E2) enemy2;
//                 System.out.println("Level 2 - Enemy 2 Power: " + e2Enemy2.power);
//             }
//         }

       
//     }
// }
