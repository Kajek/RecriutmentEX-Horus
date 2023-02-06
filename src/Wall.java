import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/*
W odpowiedzi na zainteresowanie naszą ofertą pracy chcielibyśmy zaprosić Panią do pierwszego etapu rekrutacji na stanowisko Junior Java Developer w firmie Horus.
Poniżej przekazujemy zadanie z prośbą o analizę poniższego kodu i zaimplementowanie metod findBlockByColor, findBlocksByMaterial, count w klasie Wall -
 najchętniej unikając powielania kodu i umieszczając całą logikę w klasie Wall. Z uwzględnieniem w analizie i implementacji interfejsu CompositeBlock!

*/
public class Wall implements Structure {
    private List<Block> blocks;

    public Wall() {
        this.blocks = new ArrayList<>();
    }

    public void addBlock(Block block) {
        blocks.add(block);
    }

    @Override
    public Optional<Block> findBlockByColor(String color) {
        return getBlocksList(blocks).stream().filter(block -> block.getColor().equals(color)).findAny();
    }

    @Override
    public List<Block> findBlocksByMaterial(String material) {
        return getBlocksList(blocks).stream().filter(block -> block.getMaterial().equals(material)).collect(Collectors.toList());
    }

    @Override
    public int count() {
        return getBlocksList(blocks).size();
    }

    private List<Block> getBlocksList(List<Block> blockList) {

        List<Block> tempList = new ArrayList<>();
        for (Block b : blockList) {
            if (b instanceof CompositeBlock compositeBlock) {
                tempList.addAll(getBlocksList(compositeBlock.getBlocks()));
                tempList.add(compositeBlock);
            } else {
                tempList.add(b);
            }
        }
        return tempList;
    }
}
