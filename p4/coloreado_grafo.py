import json

from auxiliar import dibujar_mapa_coloreado, generar_mapa_grafo

def realizar_voraz(grafo):
    COLORES = ["red", "blue", "green", "yellow", "orange", "purple", "cyan", "magenta", "lime"]
    
    color_asignado = {}

    for nodo in grafo:
        colores_vecinos = set()
        for vecino in grafo[nodo]:
            if str(vecino) in color_asignado:
                colores_vecinos.add(color_asignado[str(vecino)])
        
        for color in COLORES:
            if color not in colores_vecinos:
                color_asignado[str(nodo)] = color
                break

    return color_asignado

if __name__ == "__main__":
    n = 40
    mapa = generar_mapa_grafo(n)
    solucion = realizar_voraz(mapa["grafo"])

    if solucion:
        print("Solución encontrada:", solucion)
        dibujar_mapa_coloreado(mapa, solucion)
        with open('sols/solucion.json', 'w') as f:
            json.dump(solucion, f)
            f.close()
    else:
        print("No se encontró solución.")

