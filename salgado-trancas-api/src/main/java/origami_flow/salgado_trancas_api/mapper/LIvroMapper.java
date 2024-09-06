package origami_flow.salgado_trancas_api.mapper;

import origami_flow.salgado_trancas_api.dto.LivroDto;
import origami_flow.salgado_trancas_api.dto.livro.Item;

import java.util.List;
import java.util.stream.Collectors;

public class LIvroMapper {
    public static List<LivroDto> toLivroDtoList(List<Item> items) {
        return items.stream()
                .map(LIvroMapper::toLivroDto)
                .collect(Collectors.toList());
    }

    private static LivroDto toLivroDto(Item item) {
        return LivroDto.builder()
                .id(item.getId())
                .title(item.getVolumeInfo() != null ? item.getVolumeInfo().getTitle() : null)
                .authors(item.getVolumeInfo() != null && item.getVolumeInfo().getAuthors() != null
                        ? String.join(", ", item.getVolumeInfo().getAuthors()) : null)
                .publishedDate(item.getVolumeInfo() != null ? item.getVolumeInfo().getPublishedDate() : null)
                .smallThumbnail(item.getVolumeInfo() != null && item.getVolumeInfo().getImageLinks() != null
                        ? item.getVolumeInfo().getImageLinks().getSmallThumbnail() : null)
                .thumbnail(item.getVolumeInfo() != null && item.getVolumeInfo().getImageLinks() != null
                        ? item.getVolumeInfo().getImageLinks().getThumbnail() : null)
                .previewLink(item.getVolumeInfo() != null ? item.getVolumeInfo().getPreviewLink() : null)
                .isAvailable(item.getAccessInfo() != null && item.getAccessInfo().getPdf() != null
                        ? item.getAccessInfo().getPdf().getIsAvailable() : null)
                .downloadLink(item.getAccessInfo() != null && item.getAccessInfo().getPdf() != null
                        ? item.getAccessInfo().getPdf().getDownloadLink() : null)
                .build();
    }
}
