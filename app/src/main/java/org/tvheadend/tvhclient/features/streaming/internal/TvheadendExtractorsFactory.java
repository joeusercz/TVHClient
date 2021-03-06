/*
 * Copyright (c) 2017 Kiall Mac Innes <kiall@macinnes.ie>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.tvheadend.tvhclient.features.streaming.internal;

import android.content.Context;

import com.google.android.exoplayer2.extractor.Extractor;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.extractor.flv.FlvExtractor;
import com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor;
import com.google.android.exoplayer2.extractor.mp3.Mp3Extractor;
import com.google.android.exoplayer2.extractor.mp4.FragmentedMp4Extractor;
import com.google.android.exoplayer2.extractor.mp4.Mp4Extractor;
import com.google.android.exoplayer2.extractor.ogg.OggExtractor;
import com.google.android.exoplayer2.extractor.ts.Ac3Extractor;
import com.google.android.exoplayer2.extractor.ts.AdtsExtractor;
import com.google.android.exoplayer2.extractor.ts.PsExtractor;
import com.google.android.exoplayer2.extractor.ts.TsExtractor;
import com.google.android.exoplayer2.extractor.wav.WavExtractor;

class TvheadendExtractorsFactory implements ExtractorsFactory {

    private final Context mContext;

    TvheadendExtractorsFactory(Context context) {
        mContext = context;
    }

    @Override
    public Extractor[] createExtractors() {
        Extractor[] extractors = new Extractor[12];

        extractors[0] = new HtspExtractor(mContext);
        extractors[1] = new MatroskaExtractor(0);
        extractors[2] = new FragmentedMp4Extractor(0);
        extractors[3] = new Mp4Extractor();
        extractors[4] = new Mp3Extractor(0);
        extractors[5] = new AdtsExtractor();
        extractors[6] = new Ac3Extractor();
        extractors[7] = new TsExtractor(0);
        extractors[8] = new FlvExtractor();
        extractors[9] = new OggExtractor();
        extractors[10] = new PsExtractor();
        extractors[11] = new WavExtractor();

        return extractors;
    }
}
