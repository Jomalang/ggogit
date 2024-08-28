package Recorders.ggogit.domain.memoir;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemoirRepository {
    List<Memoir> findAll();

    Memoir findById(int id);

    Memoir save(Memoir memoir);

    void delete(Memoir memoir);

    Memoir update(Memoir memoir);
}
