package com.twodo0.capstoneWeb.service.mapper;


import com.twodo0.capstoneWeb.domain.ClassProb;
import com.twodo0.capstoneWeb.domain.Detection;
import com.twodo0.capstoneWeb.dto.FastApiPredictRes;

import java.util.List;

public class FastApiMapper {

    public static List<ClassProb> toClassProbs(List<FastApiPredictRes.ApiClassProbDto> src){
        if (src == null || src.isEmpty()) return List.of();
        return src.stream().
                map(c -> new ClassProb(c.damageType(), c.prob()))
                .toList();
    }

    public static List<Detection> toDetections(List<FastApiPredictRes.BoxDto> src) {
        if(src == null || src.isEmpty()) return List.of();

        return src.stream()
                .map(b -> {
            int x = safeRound(b.x()); int y = safeRound(b.y());
            int w = safeRound(b.w()); int h = safeRound(b.h());
            List<ClassProb> probs = toClassProbs(b.classProbs());

            return Detection.builder()
                    .classProbs(probs)
                    .x(x)
                    .y(y)
                    .w(w)
                    .h(h).build();
        }).toList();
    }

    // 좌표 반올림 처리 (double -> int)
    private static int safeRound(Double v) {
        if ((v == null) || !Double.isFinite(v)) return 0;
        return (int) Math.round(v);
    }
}
