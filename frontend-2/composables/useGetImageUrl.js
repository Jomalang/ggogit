export default function useGetImageUrl(imageFile = "", imageType = "book") {
  const config = useRuntimeConfig();

  if (imageFile === null || imageFile === "" || imageFile === undefined) {
    if (imageType === "memberBackground") return `/png/background-image.png`;
    return `/png/tree-icon-white.png`; // TODO 디폴트 이미지 처리를 해줘야 함
  }

  if (imageFile.substring(0, 4) === "http") {
    return imageFile;
  }
  //이미지가 Base64면 Base64 반환
  else if (imageFile.substring(0, 5) === "data:") {
    return imageFile;
  } else {
    return `${config.public.apiBase}/images/${imageType}/${imageFile}`;
  }
}
