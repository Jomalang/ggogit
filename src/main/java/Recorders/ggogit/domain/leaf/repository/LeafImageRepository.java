package Recorders.ggogit.domain.leaf.repository;

import Recorders.ggogit.domain.leaf.entity.LeafImage;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LeafImageRepository {
    Long save(LeafImage leafImage);
}