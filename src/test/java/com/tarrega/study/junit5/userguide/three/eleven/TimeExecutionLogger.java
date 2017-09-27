package com.tarrega.study.junit5.userguide.three.eleven;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.extension.ExtendWith;

@Tag("timed")
@ExtendWith(TimingExtension.class)
interface TimeExecutionLogger {
}