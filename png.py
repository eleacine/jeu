from PIL import Image, ImageDraw

# Créer une image de fond blanche
width, height = 150, 50
image = Image.new("RGB", (width, height), "white")
draw = ImageDraw.Draw(image)

# Dessiner le carré vert
draw.rectangle([0, 0, 50, 50], fill="green")

# Dessiner le carré blanc
draw.rectangle([50, 0, 100, 50], fill="white")

# Dessiner le carré rouge
draw.rectangle([100, 0, 150, 50], fill="red")

# Enregistrer l'image en tant que fichier PNG
image.save("trois_carres.png")