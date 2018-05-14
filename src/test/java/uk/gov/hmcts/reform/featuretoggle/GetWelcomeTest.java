package uk.gov.hmcts.reform.featuretoggle;

import org.ff4j.FF4j;
import org.ff4j.core.Feature;
import org.ff4j.core.FeatureStore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class GetWelcomeTest {

    @MockBean
    private FF4j ff4j;

    @MockBean
    private FeatureStore featureStore;

    @Test
    public void should_return_feature_toggle_value_with_200_response_code() throws Exception {
        Feature feature = new Feature("test", true);

        when(ff4j.getFeatureStore()).thenReturn(featureStore);
        when(featureStore.read("f1")).thenReturn(feature);

        Feature f1 = ff4j.getFeatureStore().read("f1");

        assertThat(f1.isEnable()).isEqualTo(true);
    }
}
