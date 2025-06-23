package pcbuilder.service;

import pcbuilder.components.PlacaDeVideo;
import pcbuilder.factory.ComponenteFactory;
import pcbuilder.components.Marca;

import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class PlacaDeVideoService extends ComponenteService<PlacaDeVideo> {
    private static final String ARQUIVO = "data/placas_video.json";

    public PlacaDeVideoService() {
        carregarDeJson();
        if (storage.isEmpty()) {
            storage.add(
                ComponenteFactory.criarPlacaDeVideo(
                    "NVIDIA GeForce RTX 3060",
                    220000,
                    "Placa de vídeo com 12GB GDDR6 e suporte a Ray Tracing",
                    Marca.NVIDIA,
                    "/images/rtx3060.png",
                    12,
                    "Ampere"
                )
            );
            storage.add(
                ComponenteFactory.criarPlacaDeVideo(
                    "AMD Radeon RX 6700 XT",
                    200000,
                    "Placa de vídeo com 12GB GDDR6 e arquitetura RDNA 2",
                    Marca.AMD,
                    "/images/rx6700xt.png",
                    12,
                    "RDNA 2"
                )
            );
            storage.add(
                ComponenteFactory.criarPlacaDeVideo(
                    "Intel Arc A770",
                    180000,
                    "Placa de vídeo com 16GB GDDR6 e DLSS-like XeSS",
                    Marca.INTEL,
                    "/images/arc_a770.png",
                    16,
                    "Xe-HPG"
                )
            );
            salvarEmJson();
        }
    }

    public void salvarEmJson() {
        try {
            Gson gson = new Gson();
            String json = gson.toJson(storage);
            Files.write(Paths.get(ARQUIVO), json.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void carregarDeJson() {
        try {
            if (Files.exists(Paths.get(ARQUIVO))) {
                String json = new String(Files.readAllBytes(Paths.get(ARQUIVO)));
                Type listType = new TypeToken<List<PlacaDeVideo>>() {}.getType();
                List<PlacaDeVideo> placas = new Gson().fromJson(json, listType);
                storage.clear();
                storage.addAll(placas);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void salvarOuAtualizar(PlacaDeVideo comp) {
        super.salvarOuAtualizar(comp);
        salvarEmJson();
    }

    @Override
    public void excluir(String nome) {
        super.excluir(nome);
        salvarEmJson();
    }
}
