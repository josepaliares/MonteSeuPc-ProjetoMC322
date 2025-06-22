package pcbuilder.service;

import pcbuilder.components.MemoriaRAM;
import pcbuilder.factory.ComponenteFactory;
import pcbuilder.components.Marca;

import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class MemoriaRAMService extends ComponenteService<MemoriaRAM> {
    private static final String ARQUIVO = "memorias.json";

    public MemoriaRAMService() {
        carregarDeJson();
        if (storage.isEmpty()) {
            storage.add(
                ComponenteFactory.criarMemoriaRAM(
                    "Corsair Vengeance LPX",
                    45000,
                    "Memória DDR4 16GB (2x8GB) 3200MHz",
                    Marca.INTEL,
                    "/images/vengeance_lpx.png",
                    16,
                    3200,
                    "DDR4"
                )
            );
            storage.add(
                ComponenteFactory.criarMemoriaRAM(
                    "Kingston Fury Beast",
                    80000,
                    "Memória DDR5 32GB (2x16GB) 5200MHz",
                    Marca.AMD,
                    "/images/fury_beast_ddr5.png",
                    32,
                    5200,
                    "DDR5"
                )
            );
            storage.add(
                ComponenteFactory.criarMemoriaRAM(
                    "G.Skill Ripjaws V",
                    30000,
                    "Memória DDR4 8GB 2666MHz",
                    Marca.NVIDIA,
                    "/images/ripjaws_v.png",
                    8,
                    2666,
                    "DDR4"
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
                Type listType = new TypeToken<List<MemoriaRAM>>() {}.getType();
                List<MemoriaRAM> memorias = new Gson().fromJson(json, listType);
                storage.clear();
                storage.addAll(memorias);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void salvarOuAtualizar(MemoriaRAM comp) {
        super.salvarOuAtualizar(comp);
        salvarEmJson();
    }

    @Override
    public void excluir(String nome) {
        super.excluir(nome);
        salvarEmJson();
    }
}
