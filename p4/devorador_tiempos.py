import json
import time
import sys
 
from coloreado_grafo import realizar_voraz
 
if __name__ == "__main__":
    repeticiones = int(sys.argv[1])
 
    tamaños = [4, 8, 16, 32, 64, 100, 128, 256, 512, 1024, 2048, 4096, 8192, 16384, 32768, 65536]
 
    for n in tamaños:
        archivo = f"sols/g{n}.json"
        try:
            with open(archivo) as f:
                mapa = json.load(f)
            grafo = mapa["grafo"]
 
            t = 0
            for i in range(repeticiones):
                t1 = time.time()
                realizar_voraz(grafo)
                t2 = time.time()
                t += (t2 - t1) * 1000  # en ms
 
            print(f"n={n}\tTiempo={t:.2f}\tRepeticiones={repeticiones}")
 
        except FileNotFoundError:
            print(f"n={n}\tarchivo no encontrado")
 