import React, { useRef } from 'react';
import { SafeAreaView, Button, View } from 'react-native';
import { WebView } from 'react-native-webview';


const App = () => {
  const webViewRef = useRef(null);

  const goBack = () => {
    if (webViewRef.current) {
      webViewRef.current.goBack();
    }
  };

  return (
      <SafeAreaView style={{ flex: 1 }}>
        <View style={{ flex: 1 }}>
          <WebView
              ref={webViewRef}
              source={{ uri: 'http://localhost:5173/' }}
              style={{ flex: 1, marginTop: 30 }}
          />
          <Button title="Go Back" onPress={goBack} />
        </View>
      </SafeAreaView>
  );
};

export default App;
