package pcbuilder.service;

import pcbuilder.components.SSDNVME;
import pcbuilder.factory.ComponenteFactory;
import pcbuilder.components.Marca;

import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class SSDNVMEService extends ComponenteService<SSDNVME> {
    private static final String ARQUIVO = "data/ssdsnvme.json";

    public SSDNVMEService() {
        carregarDeJson(); 
        if (storage.isEmpty()) {
            storage.add(
                ComponenteFactory.criarSSDNVME(
                    "Kingston NV2",
                    28000,
                    "SSD NVMe 500GB Gen4 x4, leitura até 3500MB/s",
                    Marca.KINGSTON,
                    "/images/kingston_nv2.png",
                    500,
                    80 
                )
            );
            storage.add(
                ComponenteFactory.criarSSDNVME(
                    "Samsung 980 Pro",
                    56000,
                    "SSD NVMe 1TB Gen4 x4, leitura até 7000MB/s",
                    Marca.SAMSUNG,
                    "/images/samsung_980pro.png",
                    1000,
                    80
                )
            );
            storage.add(
                ComponenteFactory.criarSSDNVME(
                    "Western Digital SN850X",
                    64000,
                    "SSD NVMe 1TB Gen4 x4, leitura até 7300MB/s",
                    Marca.SAMSUNG,
                    "/images/wd_sn850x.png",
                    1000,
                    80
                )
            );
            salvarEmJson(); 
        }
    }

    @Override
    public void salvarOuAtualizar(SSDNVME comp) {
        super.salvarOuAtualizar(comp);
        salvarEmJson();
    }

    @Override
    public void excluir(String nome) {
        super.excluir(nome);
        salvarEmJson();
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
                Type listType = new TypeToken<List<SSDNVME>>(){}.getType();
                List<SSDNVME> ssds = new Gson().fromJson(json, listType);
                storage.clear();
                storage.addAll(ssds);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
