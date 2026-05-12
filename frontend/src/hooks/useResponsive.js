import { useWindowDimensions, Platform } from 'react-native';

export function useResponsive() {
  const { width, height } = useWindowDimensions();

  const isWeb      = Platform.OS === 'web';
  const isDesktop  = isWeb && width >= 1024;
  const isTablet   = width >= 768 && width < 1024;
  const isMobile   = width < 768;

  return { isDesktop, isTablet, isMobile, isWeb, width, height };
}
