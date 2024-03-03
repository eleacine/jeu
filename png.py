from PIL import Image, ImageDraw

# Créer une image de fond blanche
width, height = 120, 40
image = Image.new("RGB", (width, height), "white")
draw = ImageDraw.Draw(image)

# Dessiner le carré vert
draw.rectangle([0, 0, 40, 40], fill="green")

# Dessiner le carré blanc
draw.rectangle([40, 0, 80, 40], fill="white")

# Dessiner le carré rouge
draw.rectangle([80, 0, 120, 40], fill="red")

# Enregistrer l'image en tant que fichier PNG
image.save("trois_carres.png")