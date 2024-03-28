from PIL import Image, ImageOps
import os

# Chemin du répertoire du script
repertoire_script = os.path.dirname(os.path.abspath(__file__))

# Liste des noms des fichiers PNG
chemins_images = ["1.png", "2.png", "3.png", "7.png", "8.png", "9.png", "10.png", "11.png", "12.png", "13.png","14.png","15.png","16.png","17.png","18.png","19.png","20.png","21.png"]

# Création d'une nouvelle image vide de 40x40 pixels (avec fond transparent)
nouvelle_image = Image.new('RGBA', (40*9, 40*2), (0, 0, 0, 0))

# Coordonnées de départ pour placer les images dans la nouvelle image
x, y = 0, 0

# Parcours des images et collage dans la nouvelle image
for chemin_image in chemins_images:
    chemin_complet = os.path.join(repertoire_script, chemin_image)
    image = Image.open(chemin_complet)
    nouvelle_image.paste(image, (x, y))
    x += 40  # Déplacement vers la droite pour la prochaine image
    if x == 40 * 9:  # Une nouvelle ligne après chaque 3 images
        x = 0
        y += 40

# Sauvegarde de l'image combinée
nouvelle_image.save(os.path.join(repertoire_script, "image_combinee.png"))

# Affichage de l'image combinée
nouvelle_image.show()